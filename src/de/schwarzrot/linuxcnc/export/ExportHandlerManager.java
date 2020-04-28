package de.schwarzrot.linuxcnc.export;
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


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import de.schwarzrot.jar.JarInfo;


public class ExportHandlerManager {
   public ExportHandlerManager(File baseDir) {
      this.baseDir = baseDir;
      findExportHandlers();
   }


   public ExportHandlerManager(String baseDir) {
      this(new File(baseDir));
   }


   public IExportHandler getHandler(String handlerName) {
      if (!imExportHandlers.containsKey(handlerName))
         return null;
      JarInfo        jarInfo = imExportHandlers.get(handlerName);
      IExportHandler rv      = null;

      try {
         rv = loadHandler(jarInfo);
      } catch (Throwable t) {
         t.printStackTrace();
      }
      return rv;
   }


   protected void findExportHandlers() {
      imExportHandlers = new HashMap<String, JarInfo>();
      JarInfo ji = null;

      for (File f : baseDir.listFiles()) {
         if (f.getName().startsWith("."))
            continue;
         if (f.getName().endsWith(".jar")) {
            System.out.println("check file: " + f.getAbsolutePath());
            if ((ji = inspectArchive(f.getAbsoluteFile())) != null) {
               ji.setName(f.getName().substring(0, f.getName().length() - 4));
               imExportHandlers.put(ji.getName(), ji);
            }
         }
      }

      System.out.println("\n\nfound these export handlers: ");
      for (String n : imExportHandlers.keySet()) {
         System.out.println("exHdr: " + n);
      }
   }


   protected JarInfo inspectArchive(File jarFile) {
      JarFile jf      = null;
      JarInfo jarInfo = null;

      try {
         jf = new JarFile(jarFile);
         Manifest   m     = jf.getManifest();
         Attributes attrs = m.getMainAttributes();

         for (Object k : attrs.keySet()) {
            if (ImpType.compareTo(k.toString()) == 0) {
               if (ExHdr.compareTo(attrs.get(k).toString()) == 0) {
                  jarInfo = new JarInfo();
                  jarInfo.setPath(jarFile);
               }
               break;
            }
         }

         if (jarInfo != null) {
            Enumeration<JarEntry> jarEntries = jf.entries();

            while (jarEntries.hasMoreElements()) {
               JarEntry je    = jarEntries.nextElement();
               String[] parts = je.getName().split("/");
               String   name  = parts[parts.length - 1];

               // skip inner classes
               if (name.contains("$"))
                  continue;

               if (!je.isDirectory() && name.endsWith(".class")) {
                  // System.out.println("\tfound potential handler: " + name);
                  name = je.getName().replace('/', '.');

                  jarInfo.setClassName(name.substring(0, name.length() - 6));
               }
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (jf != null) {
            try {
               jf.close();
            } catch (IOException e) {
            }
         }
      }
      return jarInfo;
   }


   @SuppressWarnings({ "rawtypes", "unchecked" })
   protected IExportHandler loadHandler(JarInfo jarInfo) {
      IExportHandler rv = null;
      StringBuilder  ub = new StringBuilder("file://");

      ub.append(jarInfo.getPath());
      URL url = null;

      try {
         url = new URL(ub.toString());
         URLClassLoader ucl     = URLClassLoader.newInstance(new URL[] { url });
         Class          ehClass = ucl.loadClass(jarInfo.getClassName());
         Constructor    c       = ehClass.getDeclaredConstructor(new Class[0]);
         Object         ni      = c.newInstance(new Object[0]);

         if (ni instanceof IExportHandler) {
            rv = (IExportHandler) ni;
         }
      } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
            | InstantiationException | IllegalAccessException | IllegalArgumentException
            | InvocationTargetException e) {
         e.printStackTrace();
      }
      return rv;
   }


   private File                 baseDir;
   private Map<String, JarInfo> imExportHandlers;
   private static final String  ExHdr   = "ExportHandler";
   private static final String  ImpType = "Implementation-Type";
}
