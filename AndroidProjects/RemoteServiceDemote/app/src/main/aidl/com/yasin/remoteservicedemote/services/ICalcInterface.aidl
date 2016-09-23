// ICalcInterface.aidl
package com.yasin.remoteservicedemote.services;

// Declare any non-default types here with import statements

interface ICalcInterface {
   //定义AIDL中提供的接口，相当于告诉其他应用程序可以调用的方法

   /**
   *
   *对外定义一个add（）方法
   */
   int add(int a ,int b);



}
