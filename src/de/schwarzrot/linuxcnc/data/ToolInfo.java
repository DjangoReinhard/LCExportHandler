package de.schwarzrot.linuxcnc.data;
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

public class ToolInfo {
   public String getCoating() {
      return coating;
   }


   public double getColletDiameter() {
      return colletDiameter;
   }


   public double getColletLength() {
      return colletLength;
   }


   public String getComment() {
      return comment;
   }


   public double getCuttingAngle() {
      return cuttingAngle;
   }


   public double getCuttingLength() {
      return cuttingLength;
   }


   public double getCuttingRadius() {
      return cuttingRadius;
   }


   public double getFluteDiameter() {
      return fluteDiameter;
   }


   public double getFluteLength() {
      return fluteLength;
   }


   public int getFlutes() {
      return flutes;
   }


   public double getFreeLength() {
      return freeLength;
   }


   public double getHelixAngle() {
      return helixAngle;
   }


   public String getMaterial() {
      return material;
   }


   public double getMaxRampAngle() {
      return maxRampAngle;
   }


   public String getNote() {
      return note;
   }


   public String getPartCode() {
      return partCode;
   }


   public String getProfile() {
      return profile;
   }


   public double getShankDiameter() {
      return shankDiameter;
   }


   public double getSlopeAngle() {
      return slopeAngle;
   }


   public double getTipDiameter() {
      return tipDiameter;
   }


   public String getToolName() {
      return toolName;
   }


   public int getToolNumber() {
      return toolNumber;
   }


   public double getToothLoad() {
      return toothLoad;
   }


   public void setCoating(String coating) {
      this.coating = coating;
   }


   public void setColletDiameter(double colletDiameter) {
      this.colletDiameter = colletDiameter;
   }


   public void setColletLength(double colletLength) {
      this.colletLength = colletLength;
   }


   public void setComment(String comment) {
      this.comment = comment;
   }


   public void setCuttingAngle(double cuttingAngle) {
      this.cuttingAngle = cuttingAngle;
   }


   public void setCuttingLength(double cuttingLength) {
      this.cuttingLength = cuttingLength;
   }


   public void setCuttingRadius(double cuttingRadius) {
      this.cuttingRadius = cuttingRadius;
   }


   public void setFluteDiameter(double fluteDiameter) {
      this.fluteDiameter = fluteDiameter;
   }


   public void setFluteLength(double fluteLength) {
      this.fluteLength = fluteLength;
   }


   public void setFlutes(int flutes) {
      this.flutes = flutes;
   }


   public void setFreeLength(double freeLength) {
      this.freeLength = freeLength;
   }


   public void setHelixAngle(double helixAngle) {
      this.helixAngle = helixAngle;
   }


   public void setMaterial(String material) {
      this.material = material;
   }


   public void setMaxRampAngle(double maxRampAngle) {
      this.maxRampAngle = maxRampAngle;
   }


   public void setNote(String note) {
      this.note = note;
   }


   public void setPartCode(String partCode) {
      this.partCode = partCode;
   }


   public void setProfile(String profile) {
      this.profile = profile;
   }


   public void setShankDiameter(double shankDiameter) {
      this.shankDiameter = shankDiameter;
   }


   public void setSlopeAngle(double slopeAngle) {
      this.slopeAngle = slopeAngle;
   }


   public void setTipDiameter(double tipDiameter) {
      this.tipDiameter = tipDiameter;
   }


   public void setToolName(String toolName) {
      this.toolName = toolName;
   }


   public void setToolNumber(int toolNumber) {
      this.toolNumber = toolNumber;
   }


   public void setToothLoad(double toothLoad) {
      this.toothLoad = toothLoad;
   }

   private int    toolNumber;
   private String toolName;
   private String profile;
   private double colletDiameter;
   private double colletLength;
   private double shankDiameter;
   private double freeLength;
   private double slopeAngle;
   private int    flutes;
   private double fluteDiameter;
   private double fluteLength;
   private double cuttingRadius;
   private double cuttingLength;
   private double cuttingAngle;
   private double tipDiameter;
   private String partCode;
   private String material;
   private String coating;
   private double toothLoad;
   private double helixAngle;
   private double maxRampAngle;
   private String comment;
   private String note;
}
