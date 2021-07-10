package de.schwarzrot.linuxcnc.data;
/*
 * **************************************************************************
 *
 *  file:   	CategoryInfo.java
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

public class CategoryInfo {
   public String getName() {
      return name == null ? profile : name;
   }


   public String getProfile() {
      return profile;
   }


   public int getType() {
      return type;
   }


   public void setName(String name) {
      this.name = name;
   }


   public void setProfile(String profile) {
      this.profile = profile;
   }


   public void setType(int type) {
      this.type = type;
   }


   private String profile;
   private String name;
   private int    type;
}
