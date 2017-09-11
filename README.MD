# Logback configuration
- __maxFileSize__: each time the current log file reaches __maxFileSize__ before the current time period ends, 
it will be archived with an increasing index, starting at 0
- __maxHistory__: controls the maximum number of archive files to keep, asynchronously deleting older files
- __totalSizeCap__: controls the total size of all archive files. Oldest archives are deleted asynchronously 
when the total size cap is exceeded. The __totalSizeCap__ property requires __maxHistory__ property to be set as 
well. Moreover, the "max history" restriction is always applied first and the "total size cap" restriction 
applied second
- __TimeBasedRollingPolicy__: supports automatic file compression. This feature is enabled if the value of the 
fileNamePattern option ends with .gz or .zip
- https://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/
- https://en.wikipedia.org/wiki/List_of_tz_database_time_zones

# TODO
- handle rotate file
- alert when exception