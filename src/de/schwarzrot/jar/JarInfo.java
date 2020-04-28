package de.schwarzrot.jar;
/* 
 * **************************************************************************
 * 
 *  file:   	JarInfo.java
 *  project:  	GUI for linuxcnc
 *  subproject:	base library for exporthandlers
 *  purpose: 	helper classes for creation of exporthandlers, that
 *           	will be used to export tooltables from toolmanager
 *  created: 	4.12.2019 by Django Reinhard
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


public class JarInfo {
   public String getClassName() {
      return className;
   }


   public String getName() {
      return name;
   }


   public File getPath() {
      return path;
   }


   public void setClassName(String className) {
      this.className = className;
   }


   public void setName(String name) {
      this.name = name;
   }


   public void setPath(File path) {
      this.path = path;
   }

   private String name;
   private File   path;
   private String className;
}
