## Script of commands

<hr>

-  ### Run the GUI
    - open `res` folder
    - Double-clicking the `image_processing_application.jar`<br><br>

   <b>Click the following button in the following sequence to perform every operation. </b>
    - `Upload` : Upload the image from the system on which user want to perform operation.
    - `Flip` : Have two sub-operation to flip the image `Horizontal` and `Vertical`.
    - `Color Transform` : Have two sub-operation to transform the image to `Greyscale` and `sepia`.
    - `Filter` : Have two sub-operation to filter the image to `Blur` and `Sharpen`.
    - `Dither` : Have direct operation to perform dither to image.
    - `Brightness` : Have two sub-operation to `bright` or `darken` the image.
    - `Split` : Have operation to split the image into three component(red, green, and blue).
    - `Combine` : Have operation to upload three image, which get directly combine to one after
      uploading the final image
    - `GrayScale` : Have six sub-operation to greyscale the image `Intensity Component`, `Luma
      Component`, `Value Component`, `Red Component`, `Green Component`, and `Blue Component`.
    - `Save` : Save the latest change image.

<br>

- ### Run the script file in command-line
    - open cmd at location : `res` -> `image_processing_application.jar`<br><br>
    - Run the `java -jar image_processing_application.jar -file textFile/submitCommand.txt`
    - Run all the command present in script file `submitCommand.txt` in one-go, which contain
      command wrote below.

<br>

-  ### Run the script file in jar file
    - open cmd at location : `res`, Open cmd at that location
    - Run the `java -jar image_processing_application.jar -text`
    - Run the following script file, to run all below command in one-go.
      ```
      run textFile/submitCommand.txt
      ```

   <b>Run the following commands in the following sequence. </b><br><br>

    - Command to `load` the file : `load assets/file-name file-name`.
      ```
      load assets/snakes.ppm snakes-ppm
      load assets/snakes.png snakes-png
      load assets/snakes.jpg snakes-jpg
      load assets/snakes.bmp snakes-bmp
      ```

    - Command to `save` the file : `save assets/file-name file-name`.
      ```
      save assets/snakes-ppm.ppm snakes-ppm
      save assets/snakes-png.png snakes-png
      save assets/snakes-jpg.jpg snakes-jpg
      save assets/snakes-bmp.bmp snakes-bmp
      ```

    - Command to `rgb-split` the file `snakes.ppm`. And save the file as `png` at provided
      location : `rgb-split file-name new-file-name`
      ```
      rgb-split snakes-ppm snakes-red snakes-green snakes-blue
      save assets/snakes-green-split-png.png snakes-green
      ```

    - Command to `rgb-combine` the three red, green, and blue image
      file `snakes-red`, `snakes-green`, and `snakes-blue`. And save the file as `jpg` at
      provided location : `rgb-combine new-file-name file-name-one file-name-second file-name-three`
      ```
      rgb-combine snakes-tint snakes-red snakes-green snakes-blue
      save assets/snakes-tint-jpg.jpg snakes-tint
      ```

    - Command to `brightness` the file `snakes.ppm`. And save the file as `bmp` at provided
      location : `brighten component file-name new-file-name`
      ```
      brighten 50 snakes-ppm snakes-brighter
      save assets/snakes-brighter-bmp.bmp snakes-brighter
          
      brighten -50 snakes-ppm snakes-darken
      save assets/snakes-darken-bmp.bmp snakes-darken
      ```

    - Command to `greyscale` the file `snakes.png` and `snakes.jpg` into all component. And save
      the file as `ppm`, `png`, `jpg`, and `bmp` at provided
      location : `greyscale component file-name new-file-name`
      ```
      greyscale value-component snakes-png snakes-value-greyscale
      save assets/snakes-value-greyscale-ppm.ppm snakes-value-greyscale
          
      greyscale luma-component snakes-png snakes-luma-greyscale
      save assets/snakes-luma-greyscale-jpg.jpg snakes-luma-greyscale
          
      greyscale intensity-component snakes-png snakes-intensity-greyscale
      save assets/snakes-intensity-greyscale-bmp.bmp snakes-intensity-greyscale
          
      greyscale red-component snakes-jpg snakes-red-greyscale
      save assets/snakes-red-greyscale-ppm.ppm snakes-red-greyscale
          
      greyscale green-component snakes-jpg snakes-green-greyscale
      save assets/snakes-green-greyscale-png.png snakes-green-greyscale
          
      greyscale blue-component snakes-jpg snakes-blue-greyscale
      save assets/snakes-blue-greyscale-bmp.bmp snakes-blue-greyscale
      ```

    - Command to `flip` the file `snakes.bmp`. And save the file as `ppm`, `png`, and `jpg` at
      provided
      location : `horizontal-flip file-name new-file-name`, `vertical-flip file-name  new-file-name`
      ```
      horizontal-flip snakes-bmp snakes-horizontal
      save assets/snakes-horizontal-ppm.ppm snakes-horizontal
          
      vertical-flip snakes-horizontal snakes-horizontal-vertical
      save assets/snakes-horizontal-vertical-png.png snakes-horizontal-vertical
          
      vertical-flip snakes-bmp snakes-vertical
      save assets/snakes-vertical-jpg.jpg snakes-vertical
      ```

    - Command to `blur` the file `snakes.ppm`. And save the file as `ppm` provided
      location : `blur file-name new-file-name`
      ```
      blur snakes-ppm snakes-blur
      save assets/snakes-blur-ppm.ppm snakes-blur
      ```

    - Command to `sharpen` the file `snakes.png`. And save the file as `png` provided
      location : `sharpen file-name new-file-name`
      ```
      sharpen snakes-png snakes-sharpen
      save assets/snakes-sharpen-png.png snakes-sharpen
      ```

    - Command to color transformations into `greyscale` the file `snakes.jpg`. And save the file
      as `jpg` provided location : `greyscale luma-component file-name new-file-name`
      ```
      greyscale luma-component snakes-png snakes-luma-greyscale
      save assets/snakes-luma-greyscale-jpg.jpg snakes-luma-greyscale
      ```

    - Command to color transformations into `sepia-tone` the file `snakes.jpg`. And save the
      file as `jpg` provided location  : `sepia-tone file-name new-file-name`
      ```
      sepia snakes-jpg snakes-sepia
      save assets/snakes-sepia-jpg.jpg snakes-sepia
      ```

    - Command to `dither` the file `snakes.bmp`. And save the file as `bmp` provided
      location  : `dither file-name new-file-name`
      ```
      dither snakes-bmp snakes-dither
      save assets/snakes-dither-bpm.bmp snakes-dither
      ```

- ### Run the script file in Project
    - Go-To `src`
    - Open file `Main.java`
    - Run the `public static void main` method

<br>

- Run the following script file, to run all below command in one-go.
    ```
    run res/textFile/submitCommand.txt
    ```

<br>

- <b>Run the following commands in the following sequence. </b><br><br>

    - Command to `load` the file `snakes.ppm`, `snakes.png`, `snakes.jpg`, `snakes.bmp`.
      ```
      load res/assets/snakes.ppm snakes-ppm
      load res/assets/snakes.png snakes-png
      load res/assets/snakes.jpg snakes-jpg
      load res/assets/snakes.bmp snakes-bmp
      ```

    - Command to `rgb-split` the file `snakes.ppm`. And save the file as `png` at provided location.
      ```
      rgb-split snakes-ppm snakes-red snakes-green snakes-blue
      save res/assets/snakes-green-split-png.png snakes-green
      ```

    - Command to `rgb-combine` the three red, green, and blue image
      file `snakes-red`, `snakes-green`, and `snakes-blue`. And save the file as `jpg` at provided
      location.
      ```
      rgb-combine snakes-tint snakes-red snakes-green snakes-blue
      save res/assets/snakes-tint-jpg.jpg snakes-tint
      ```

    - Command to `brightness` the file `snakes.ppm`. And save the file as `bmp` at provided
      location.
      ```
      brighten 50 snakes-ppm snakes-brighter
      save res/assets/snakes-brighter-bmp.bmp snakes-brighter
      
      brighten -50 snakes-ppm snakes-darken
      save res/assets/snakes-darken-bmp.bmp snakes-darken
      ```

    - Command to `greyscale` the file `snakes.png` and `snakes.jpg` into all component. And save
      the file as `ppm`, `png`, `jpg`, and `bmp` at provided location.
      ```
      greyscale value-component snakes-png snakes-value-greyscale
      save res/assets/snakes-value-greyscale-ppm.ppm snakes-value-greyscale
      
      greyscale luma-component snakes-png snakes-luma-greyscale
      save res/assets/snakes-luma-greyscale-jpg.jpg snakes-luma-greyscale
      
      greyscale intensity-component snakes-png snakes-intensity-greyscale
      save res/assets/snakes-intensity-greyscale-bmp.bmp snakes-intensity-greyscale
      
      greyscale red-component snakes-jpg snakes-red-greyscale
      save res/assets/snakes-red-greyscale-ppm.ppm snakes-red-greyscale
      
      greyscale green-component snakes-jpg snakes-green-greyscale
      save res/assets/snakes-green-greyscale-png.png snakes-green-greyscale
      
      greyscale blue-component snakes-jpg snakes-blue-greyscale
      save res/assets/snakes-blue-greyscale-bmp.bmp snakes-blue-greyscale
      ```

    - Command to `flip` the file `snakes.bmp`. And save the file as `ppm`, `png`, and `jpg` at
      provided location.
      ```
      horizontal-flip snakes-bmp snakes-horizontal
      save res/assets/snakes-horizontal-ppm.ppm snakes-horizontal
      
      vertical-flip snakes-horizontal snakes-horizontal-vertical
      save res/assets/snakes-horizontal-vertical-png.png snakes-horizontal-vertical
      
      vertical-flip snakes-bmp snakes-vertical
      save res/assets/snakes-vertical-jpg.jpg snakes-vertical
      ```

    - Command to `blur` the file `snakes.ppm`. And save the file as `ppm` provided location.
      ```
      blur snakes-ppm snakes-blur
      save res/assets/snakes-blur-ppm.ppm snakes-blur
      ```

    - Command to `sharpen` the file `snakes.png`. And save the file as `png` provided location.
      ```
      sharpen snakes-png snakes-sharpen
      save res/assets/snakes-sharpen-png.png snakes-sharpen
      ```

    - Command to color transformations into `greyscale` the file `snakes.jpg`. And save the file
      as `jpg` provided location.
      ```
      greyscale luma-component snakes-png snakes-luma-greyscale
      save res/assets/snakes-luma-greyscale-jpg.jpg snakes-luma-greyscale
      ```

    - Command to color transformations into `sepia-tone` the file `snakes.jpg`. And save the file
      as `jpg` provided location.
      ```
      sepia snakes-jpg snakes-sepia
      save res/assets/snakes-sepia-jpg.jpg snakes-sepia
      ```

    - Command to `dither` the file `snakes.bmp`. And save the file as `bmp` provided location.
      ```
      dither snakes-bmp snakes-dither
      save res/assets/snakes-dither-bpm.bmp snakes-dither
      ```