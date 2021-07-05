package de.schwarzrot.linuxcnc.data

/* 
 * **************************************************************************
 * 
 *  file:   	ToolInfo.java
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
class ToolInfo {
    var toolNumber = 0
    var toolName: String? = null
    var profile: String? = null
    var colletDiameter = 0.0
    var colletLength = 0.0
    var shankDiameter = 0.0
    var freeLength = 0.0
    var slopeAngle = 0.0
    var flutes = 0
    var fluteDiameter = 0.0
    var fluteLength = 0.0
    var cuttingRadius = 0.0
    var cuttingLength = 0.0
    var cuttingAngle = 0.0
    var tipDiameter = 0.0
    var partCode: String? = null
    var material: String? = null
    var coating: String? = null
    var toothLoad = 0.0
    var helixAngle = 0.0
    var maxRampAngle = 0.0
    var comment: String? = null
    var note: String? = null
}