Hive Common UDF Library
===========================

The Hive Common UDF Library is a package that contains Apache Hive User Defined Functions (UDFs).

These UDFs have been created due to their reusability.  The build script for this common library will create an RPM and a DEB package for installation to a common location on CentOS or Ubuntu servers.  The function definition files will also be placed in a common location so they can be included with SOURCE $HIVE_COMMON_UDF/{function.name}.hql.  This will load the jar into hive and define the function name and associated with the UDF.

Requirements
---------------------------
- <a href="http://www.gradle.org">Gradle</a>


Build
-----
  *  Modify build.gradle so that it will point to your corrrect version of hadoop and hive.  It defaults to cdh4.3.0 .
  *  Execute 'gradle build'
  *  Copy __build/libs__ to __<install_dir>/lib__ .  <install_dir> is the directory you want to install these libraries to.  On our systems we use /usr/lib/hive-common-udf
  *  Copy __build/resources/main/hql__ to __<install_dir>/hql__
  *  Execute '__export HIVE_COMMON_UDF=<install_dir>__'
  
Other Information
-----------------
If you would like an example on how to add the hive-common-udf jar to your hive queries, take a look at __<install_dir>/hql/base.hql__ . 






