#!/bin/bash

sed -r 's/export XDATA_COMMON_UDF\=.*$//g' /etc/environment > /etc/environment
