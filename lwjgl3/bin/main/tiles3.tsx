<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.11.0" name="tiles3" tilewidth="64" tileheight="64" tilecount="8" columns="8">
 <image source="tiles3.png" width="512" height="64"/>
 <tile id="3" type="flag"/>
 <tile id="4">
  <objectgroup draworder="index" id="3">
   <object id="2" x="0" y="0">
    <properties>
     <property name="friction" type="float" value="100"/>
    </properties>
    <polyline points="0,0 64,0"/>
   </object>
   <object id="3" x="0" y="0">
    <polyline points="0,0 0,64"/>
   </object>
   <object id="4" x="0" y="64">
    <polyline points="0,0 64,0"/>
   </object>
   <object id="5" x="64" y="0">
    <polyline points="0,0 0,64"/>
   </object>
  </objectgroup>
 </tile>
</tileset>
