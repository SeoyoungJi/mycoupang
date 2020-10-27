package com.mycoupang.util;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class PcResource {
   
   static double kb = 1024.0;
   static double mb = 1024.0 * 1024.0;
   static double gb = 1024.0 * 1024.0 * 1024;

      
   /* 디스크의 크기 확인 */
   public HashMap<String, Integer> showDisk(){
            
      File root = null;
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      
      try
      {
         root = new File( "C:/" );
         
         //전체 디스크 공간
         System.out.println( "Total  Space: " + (int)(root.getTotalSpace()/gb) + "GB" );
         int totalSpace = (int)(root.getTotalSpace()/gb);
         
         //사용 가능한 디스크 공간
         System.out.println( "Usable Space: " + (int)(root.getUsableSpace()/gb) + "GB" );
         int usableSpace = (int)(root.getUsableSpace()/gb);
         
         //사용중인 디스크 공간
         int usingSpace = totalSpace - usableSpace;
         System.out.println( "usingSpace: "+usingSpace );
         
   
         map.put("totalSpace", totalSpace);
         map.put("usableSpace", usableSpace);
         map.put("usingSpace", usingSpace);

      }
      catch ( Exception e )
      {
         e.printStackTrace( );
      }      
      
      return map;
      
   }
   
   /* cpu 사용량 */
   public double showCPU() {
   
      final OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
      double load = 0;
      
      load = ((com.sun.management.OperatingSystemMXBean) osBean).getSystemCpuLoad();    
      
      return load*100.0;
               
   }
   
   /* 시스템 메모리 */
   public HashMap<String, Integer> showSystemMemory () {
          
	  HashMap<String, Integer> map = new HashMap<String, Integer>(); 
      Runtime runtime = Runtime.getRuntime();
      
      int total = (int)(runtime.totalMemory()/mb);
      int free = (int)(runtime.freeMemory()/mb);
      int used = total - free;
             
      map.put("totalMemory", total);
      map.put("freeMemory", free);
      map.put("usedMemory", used);
      
      return map;
   }

   
  
   public static void main(String[] args) {

      PcResource resource = new PcResource();

   }
}