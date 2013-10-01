#!/bin/bash

ENVFILE=/etc/environment

# check to see if the environment variable is already set
if ! grep -q 'export HIVE_COMMON_UDF=' $ENVFILE; then
    echo 'export HIVE_COMMON_UDF=/usr/lib/hive-common-udf' >> $ENVFILE;
else
    sed -r 's/export HIVE_COMMON_UDF\=.*$/export HIVE_COMMON_UDF=\/usr\/lib\/xdata-common-udf/g' /etc/environment > /etc/environment
fi;
