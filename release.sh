#!/bin/bash
mvn versions:update-properties
mvn release:prepare release:perform -B || exit 1
