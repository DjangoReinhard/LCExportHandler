package de.schwarzrot.linuxcnc.export

import de.schwarzrot.jar.JarInfo
import java.io.File
import java.io.IOException
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.JarFile

/* 
 * **************************************************************************
 * 
 *  file:   	ExportHandlerManager.java
 *  project:  	GUI for linuxcnc
 *  subproject:	base library for exporthandlers
 *  purpose: 	helper classes for creation of exporthandlers, that
 *           	will be used to export tooltables from toolmanager
 *  created: 	29.3.2020 by Django Reinhard
 *  copyright:	all rights reserved
 * 
 *  This program is free software: you can redistribute it and/or modify 
 *  it under the terms of the GNU General Public License as published by 
 *  the Free Software Foundation, either version 2 of the License, or 
 *  (at your option) any later version. 
 *   
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *  GNU General Public License for more details. 
 *   
 *  You should have received a copy of the GNU General Public License 
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * **************************************************************************
 */
class ExportHandlerManager(private val baseDir: File) {

    private var imExportHandlers = mutableMapOf<String?, JarInfo>()

    constructor(baseDir: String) : this(File(baseDir))

    fun getHandler(handlerName: String?): IExportHandler? {
        if (!imExportHandlers.containsKey(handlerName)) return null
        val jarInfo = imExportHandlers[handlerName]
        var rv: IExportHandler? = null
        try {
            rv = loadHandler(jarInfo)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        return rv
    }

    protected fun findExportHandlers() {
        imExportHandlers = HashMap()
        baseDir.listFiles()?.forEach { f ->
            if (f.name.startsWith(".")) return@forEach
            if (f.name.endsWith(".jar")) {
                println("check file: " + f.absolutePath)
                inspectArchive(f.absoluteFile)?.let { ji ->
                    ji.name = f.name.substring(0, f.name.length - 4)
                    imExportHandlers[ji.name] = ji
                }
            }
        }
        println("\n\nfound these export handlers: ")
        for (n in imExportHandlers.keys) {
            println("exHdr: $n")
        }
    }

    protected fun inspectArchive(jarFile: File): JarInfo? {
        var jf: JarFile? = null
        var jarPath: File? = null
        var jarClassName: String? = null
        try {
            jf = JarFile(jarFile)
            val m = jf.manifest
            val attrs = m.mainAttributes
            for (k in attrs.keys) {
                if (ImpType.compareTo(k.toString()) == 0) {
                    if (ExHdr.compareTo(attrs[k].toString()) == 0) {
                        jarPath = jarFile
                    }
                    break
                }
            }
            if (jarPath != null) {
                val jarEntries = jf.entries()
                while (jarEntries.hasMoreElements()) {
                    val je = jarEntries.nextElement()
                    val parts = je.name.split("/".toRegex()).toTypedArray()
                    var name = parts[parts.size - 1]

                    // skip inner classes
                    if (name.contains("$")) continue
                    if (!je.isDirectory && name.endsWith(".class")) {
                        // System.out.println("\tfound potential handler: " + name);
                        name = je.name.replace('/', '.')
                        jarClassName = name.substring(0, name.length - 6)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (jf != null) {
                try {
                    jf.close()
                } catch (e: IOException) {
                }
            }
        }
        return jarPath?.let {
            JarInfo(
                it,
                jarClassName!!,

                )
        }
    }

    protected fun loadHandler(jarInfo: JarInfo?): IExportHandler? {
        var rv: IExportHandler? = null
        val ub = StringBuilder("file://")
        ub.append(jarInfo!!.path)
        val url: URL?
        try {
            url = URL(ub.toString())
            val ucl = URLClassLoader.newInstance(arrayOf<URL>(url))
            val ehClass = ucl.loadClass(jarInfo.className)
            val c = ehClass.getDeclaredConstructor(*arrayOfNulls(0))
            val ni = c.newInstance(*arrayOfNulls(0))
            if (ni is IExportHandler) {
                rv = ni
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rv
    }

    companion object {
        private const val ExHdr = "ExportHandler"
        private const val ImpType = "Implementation-Type"
    }

    init {
        findExportHandlers()
    }
}