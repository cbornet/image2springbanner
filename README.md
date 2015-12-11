# image2springbanner
Small tool written in Java to convert images to spring-boot banner text resource.
## Credits
Almost all the code comes from the Spring Boot [PR](https://github.com/spring-projects/spring-boot/pull/4647) by [Craig Burke](https://github.com/craigburke).
When the PR is available in a Spring Boot release (probably 1.4), this project will no longer be of interest.
## Get it
### Pre-built jar
Click [here](https://github.com/cbornet/image2springbanner/raw/master/bin/image2springbanner.jar) to download.
### From source
Clone the project then in the project dir enter :
```shell
mvn package
```
The jar will be in the ```target``` directory
## Usage
```shell
usage: java -jar image2springbanner.jar <IMAGE_FILE> [-c] [-d] [-M <arg>]
       [-o <arg>] [-r <arg>]
Create a Spring Boot banner from an image
 -c,--cie94                whether to use CIE94 algo (default is false)
 -d,--dark                 whether to invert image for a dark background.
                           (default is false)
 -M,--max-width <arg>      maximum width in characters of banner (default
                           is 72)
 -o,--output <arg>         output file path (default is ./banner.txt)
 -r,--aspect-ratio <arg>   correction to makes sure height is correct to
                           accomodate the fact that fonts are taller than
                           they are wide. (default is 0.5)
```
Example :
```
java -jar image2springbanner.jar src/main/resources/banner.jpg -M140 -d -o src/main/resources/banner.txt
```

