package de.schwarzrot.linuxcnc.export;
/* 
 * **************************************************************************
 * 
 *  file:   	IExportHandler.java
 *  project:  	GUI for linuxcnc
 *  subproject:	base library for exporthandlers
 *  purpose: 	helper classes for creation of exporthandlers, that
 *           	will be used to export tooltables from toolmanager
 *  created: 	3.12.2019 by Django Reinhard
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


import de.schwarzrot.linuxcnc.data.CategoryInfo;
import de.schwarzrot.linuxcnc.data.LibInfo;
import de.schwarzrot.linuxcnc.data.ToolInfo;


/**
 * ToolLibrary is organized this way: Toplevel toolLibrary gets export values by
 * {LibInfo} and has
 * Childs of Category, which can be nested. Category elements gets export values
 * by {CategoryInfo}.
 * Category can have either Category or ToolEntry as child. ToolEntry gets their
 * export values by
 * {ToolInfo}.
 * To be able to satisfy XML or even more complex requirements, each element
 * will be "opened" and
 * "closed". It is guaranteed, that open and close functions receive the
 * same/identical info
 * structure.
 * That leads to the following interface, ExportHandler must implement.
 * Additionally an ExportHandler must met these requirements:
 * 1. have a constructor without arguments (or no constructor)
 * 2. The MANIFEST of the JarFile must contain the implementor tags, especially
 * the tag 'Implementation-Type' with value 'ExportHandler'
 * 3. The 'Implementation-Version' must be in sync with the Version of this
 * Interface - to be able to
 * differentiate future interface changes.
 *
 * @author Django Reinhard
 */
public interface IExportHandler {
   public void closeCategory(CategoryInfo catInfo) throws Exception;


   public void closeLibrary(LibInfo libInfo) throws Exception;


   public void closeTool(ToolInfo toolInfo) throws Exception;


   public void openCategory(CategoryInfo catInfo) throws Exception;


   public void openLibrary(LibInfo libInfo, String fileName) throws Exception;


   public void openTool(ToolInfo toolInfo) throws Exception;


   public static final String InterfaceVersion = "0.13";
}
