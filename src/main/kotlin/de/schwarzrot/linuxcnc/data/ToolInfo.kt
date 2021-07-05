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
data class ToolInfo(
    val toolNumber: Int = 0,
    val toolName: String? = null,
    val profile: String? = null,
    val colletDiameter: Double = 0.0,
    val colletLength: Double = 0.0,
    val shankDiameter: Double = 0.0,
    val freeLength: Double = 0.0,
    val slopeAngle: Double = 0.0,
    val flutes: Int = 0,
    val fluteDiameter: Double = 0.0,
    val fluteLength: Double = 0.0,
    val cuttingRadius: Double = 0.0,
    val cuttingLength: Double = 0.0,
    val cuttingAngle: Double = 0.0,
    val tipDiameter: Double = 0.0,
    val partCode: String? = null,
    val material: String? = null,
    val coating: String? = null,
    val toothLoad: Double = 0.0,
    val helixAngle: Double = 0.0,
    val maxRampAngle: Double = 0.0,
    val comment: String? = null,
    val note: String? = null
)