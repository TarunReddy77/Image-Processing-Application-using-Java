# Image Processing Application

A desktop Java application for image processing with both GUI and CLI modes, designed with MVC and the Command Pattern. Supports reading and writing PPM, PNG, JPG, and BMP formats and includes operations like grayscale, blur, sharpen, dither, brightness, flip, split/combine RGB, and sepia.

<p align="left">
  <img alt="Java" src="https://img.shields.io/badge/Java-11+-red" />
  <img alt="License" src="https://img.shields.io/badge/license-MIT-green" />
</p>

## Features

- Flip (horizontal/vertical)
- Grayscale (value, luma, intensity, red, green, blue)
- Brightness (+/-)
- Blur, Sharpen, Sepia, Dither
- RGB Split and Combine
- GUI with histogram visualization; scriptable CLI for batch operations

## Quickstart

Prerequisites: Java 11+, Maven 3.8+

Build:
```bash
mvn -B -ntp clean package
```

Run GUI:
```bash
java -cp target/image-processing-application-1.0.0.jar Main
```

Run CLI with script:
```bash
java -jar target/image-processing-application-1.0.0.jar -file res/textFile/submitCommand.txt
```

Run CLI interactively:
```bash
java -jar target/image-processing-application-1.0.0.jar -text
```

## Screenshots

- GUI with histogram

<img alt="GUI" src="res/Image Processing Application Output Photo.png" width="720" />

- High-level architecture

<img alt="Architecture" src="res/diagram.png" width="720" />

---

## Model

<hr>

- This code defines an interface `ImageModel`, `ImageFiltersModal`, `ImageOperationsModel`,
  and `ReadModel`.
- Moreover, represents a class `ImageProcess`, `ImageFilters`, `ImageOperations`,
  and `ImageReadModel` that implement each interface for performing different image processing
  operations.
- Users can perform these operations through a GUI, jar file, command-line interface, loading a
  script file, and keyboard input.
- The application provides an `ImageModel` interface to load and save images and
  an `ImageOperations` and `ImageFiltersModal` interface to perform operations on images and
  a `ReadModel` to read the data of image which can be use by `JFrameView` to make process better.

<br>

The separation between `ImageModel` (for loading and saving images)
and `ImageOperationsModel`/`ImageFiltersModal` (for other image processing features) is intentional.
This is because most image processing tasks require the ability to load and save images, while the
specific operations performed on the images will vary based on the particular features required.

<br><br>

### Package `imagemodel`

It is the main class responsible for loading & saving images and also the class which makes a
connection with the controller. It uses a HashMap to store the loaded image details.

Overall, this code provides a model for loading, saving, and processing command of images using the
ImageModel interface and the ImageProcess class.

Defines methods :

- <b>saveImage :</b> Saving of image by taking the path and name of the image to save and writes
  the image data to the file and save it to the path location.

- <b>loadImage :</b> loading of an image to model by taking the path and name of the image to load
  and reads the image data from the file.

### Package `operationmodel`

This code represents an interface `ImageOperationsModel` & `IMageFilterModel` and its
implementation `ImageOperations` & `ImageFilter` for performing different image processing
operations. All of these methods throw `IOException` if an invalid file type is encountered.

#### ImageOperation methods :

- <b>Flip :</b> This method flips an image `horizontally` or `vertically` by swapping the
  corresponding pixels in the new image.

- <b>Greyscale :</b> This method converts an image to grayscale depending on the type of greyscale
  that is needed from the RGB values as the
  type `red-component`, `green-component`, `blue-component`, `value-component`, `luma-component`,
  and `intensity-component`.

- <b>SplitRGB :</b> This method splits an image into three grayscale images containing the `red`,
  `green`, and `blue` components respectively, and setting the corresponding pixels in the new
  images.

- <b>CombineRGB :</b> This method combines the images into a single image that gets its red, green,
  and blue components from the three images respectively and setting the corresponding pixel in the
  new image.

- <b>Brightness :</b> This method adjusts the brightness of an image by a specified amount by
  iterating through each pixel in the image and adjusting the RGB values based on the specified
  amount.

Private helper method:

- <b>checkImageContainOrNot() :</b> This method takes in an image name and checks if the image
  exists
  in the imageDatas map. If it exists, it returns the corresponding GetImageDetails object. If it
  does not exist, it throws an IOException with a message indicating that the file was not found.

#### ImageFilter methods :

- <b>Blur :</b> Applies a blur filter on the specified image and stores the result in a new image
  file with the specified name.

- <b>Sharping :</b> Applies a sharpening filter on the specified image and stores the result in a
  new an image file with the specified name.

- <b>Sepia Tone :</b> Applies a sepia filter on the specified image and stores the result in a new
  an image file with the specified name.

- <b>Dithering :</b> Applies a dithering filter on the specified image and stores the result in a
  new an image file with the specified name.

Private helper method:

- <b>imageKernelMultiple() :</b> Applies the given kernel matrix to the input image and generates a
  new image with the processed pixels.

#### Abstract class `ImageProcess`

Class `ImageOperation` extend this abstract class to save some common code among child class, which
help them to re-use.

- <b>numberMinMax() :</b> This method takes in a double value as a parameter and returns a number 0
  or 255. It first checks if the value is greater than 255, and returns 255. If the value is less
  than 0, it returns 0.

- <b>convertIntoInt() :</b> This method takes in a double value as a parameter and returns the
  nearest integer value by rounding the double value.

- <b>forLoopContains() :</b> This method takes in the height and width of an image as well as a
  BiFunction that maps pixel coordinates to pixel values. It uses a for loop to iterate over each
  row and column of the image and creates a two-dimensional array of pixels.

- <b>checkHaveSameDetailsToCombine() :</b> Checks whether the given list of GetImageDetails objects
  has the same width, height, and max value, which are required to combine the images into a single
  image. If any of these properties are different, an IOException is thrown with a corresponding
  error message.

- <b>closestValue :</b> Calculates the closest value to maxValue based on the given channel value.

### Package `imageviewmodel`

- The `ImageReadModel` class is a class that implements the `ReadModel` interface and provides
  methods to access image data. It maintains a HashMap of `GetImageDetails` objects for each image.

- The constructor takes an `ImageModal` object as a parameter, which is used to retrieve image data.

- The `getImageData` method returns a HashMap of `GetImageDetails` objects that contain the details
  for each image.

- To use the `ImageReadModel` class, create an instance of the class by passing an `ImageModal`
  object as a parameter to the constructor. Then, use the `getImageData` method to retrieve a
  HashMap of `GetImageDetails` objects for each image.

## View

<hr>

This Java class represents the view of an image processing application. It extends the `JFrame`
class and implements the `ImageView` interfaces.

The class contains various methods to handle different functionalities of the application.

- `titleLabel()` : Method creates and returns a JLabel object to display the title of the
  application.
- `addFeatures()` : Method adds different features to the application such as loading, saving,
  flipping, and splitting images.
- `loadImage()` : Method loads an image and displays it on the photo and histogram screens.
- `loadCombineImage()` : Method combines images and displays them on the combine screen.
- `clearScreen()` : Method clears the screen of any loaded images.
- `errorPopUp()` : Method displays an error message in a pop-up window.
- `getAbsolutePath()` : Method returns the absolute path of the selected image file.
- `saveImage()` : Method saves the loaded image.
- `setVisibility()` : Method sets the visibility of different elements in the application such as
  sub-operation buttons, labels, screens, and spinners.
- `combineImage()` : Method combines two images.
- `gridLayout()` : Method to handle the layout of the elements in the application.

Overall, the `JFrameView` class provides the user interface for the image processing application and
handles different functionalities of the application.

## Controller

<hr>

The Image Processing Controller is a Java program that provides a user interface to perform
various image processing tasks on an image using the <b>Command Pattern</b>.

The `control` package provides a set of methods for manipulating images and interacting with the
user interface in Java.

- Controller has an `ImageController` interface with one method inside it, which help to execute the
  command pattern to perform the different operation in this application.

- The `ImageProcessingController` class implements the `ImageController` interface and is
  responsible for controlling image processing tasks by passing values to the model.

- It takes in user input and outputs the execution result to the console.

- The constructor takes in a `Readable` object for user input and an `Appendable` object for output.

- The `imageControllerTask` method performs the different image processing tasks based on user
  input. It uses a Scanner object to read user input and a Map to map user input to the
  appropriate `PerformingImageTasks` object.

- It loops through the user input, checks if the input matches any known command, create
  a `PerformingImageTasks` object for the command and executes it. If the input is "q", "quit",
  or "exit", the method returns and the program terminates.

- The program also provides the ability to run scripts. The `Runscript` task class reads a script
  file and executes each command in the file. This feature allows users to perform a series of
  commands on an image without having to type each command individually.<br>

- The main method creates an instance of the `ImageProcessing` interface and an instance of the
  `ImageModal` interface implementation is called ImageProcess. It then calls
  the `ImageControllerTask` the method passing the `ImageProcess` object to begin image processing.

- The `IController` interface defines the contract for all controllers in the package and includes
  methods for loading, flipping, converting, applying filters, adjusting brightness and intensity,
  and separating RGB components.

- The `Controller` class is an implementation of the `IController`  interface and provides the
  implementation for all the methods. To use this package, create an instance of the `Controller`
  class and call its methods.

- It inherits from the `ImageProcessingController` class and provides the implementation for all the
  methods defined in the interface.

Overall, the ImageProcessingController class provides a flexible and extensible way of controlling
different image processing tasks through a command-line interface.

#### Interfaces:

- <b>ImageProcessing :</b> This interface acts as the main controller for the entire program.
  It receives input from the user, and based on the input, it creates the corresponding task object
  and performs the required action.

- <b>PerformingImageTasks :</b> This is an interface implemented by all the task classes. It defines
  a single method, startControl, which takes an ImageModel object and performs the task on it.

- <b>IController :</b> This is an interface implemented by all the view tasks. It defines
  a multiple method, which takes an ImageModel and ViewModel object and performs the task on it.

#### Classes :

- <b>ImageProcessingController :</b> This performs all the command and call the respective classes.

- <b>Load :</b> This class implements PerformingImageTasks and is responsible for loading an image
  from a file.

- <b>Save :</b> This class implements PerformingImageTasks and is responsible for saving the image
  to a file at the given location.

- <b>Brighten :</b> This class implements PerformingImageTasks and is responsible for
  brightening/darkening the image as per the value user wants.

- <b>Greyscale :</b> This class implements PerformingImageTasks and is responsible for converting
  the image to grayscale base on the requirement like "red-component", "green-component", "
  blue-component", "value-component", "luma-component", and "intensity-component".

- <b>VerticalFlip :</b> This class implements PerformingImageTasks and is responsible for flipping
  the image vertically.

- <b>HorizontalFlip :</b> This class implements PerformingImageTasks and is responsible for flipping
  the image horizontally.

- <b>RGBSplit :</b> This class implements PerformingImageTasks and is responsible for splitting the
  RGB channels of the image. Split the given image into three greyscale images containing its red,
  green and blue components respectively.

- <b>RGBCombine :</b> This class implements PerformingImageTasks and is responsible for combining
  the RGB channels of the image. Combine the three images into a single image that gets its red,
  green, and blue components from the three images respectively.

- <b>Runscript :</b> This class implements PerformingImageTasks and is responsible for running a
  script file containing a list of commands.

- <b>Blur :</b>This class implements PerformingImageTasks and is responsible for loading an image
  from a file.

- <b>Sharpen :</b>This class implements PerformingImageTasks and is responsible for loading an image
  from a file.

- <b>Sepia :</b>This class implements PerformingImageTasks and is responsible for loading an image
  from a file.

- <b>Dither :</b>This class implements PerformingImageTasks and is responsible for loading an image
  from a file.

- <b>Controller :</b>The Controller class is responsible for controlling the image processing
  functionality and interacting with the user interface. It implements the IController interface and
  extends the ImageProcessingController class.

## Services : Package `core`

<hr>

### Package `usecases`

The `ImageDetails` and `ImagePixel` classes are implementations of `GetImageDetails` and `Pixel`,
respectively, and used to set the image details, respectively.

- <b>GetImageDetails :</b> This interface defines methods for getting details of the original image,
  such as its width, height, max pixel, and pixel values at different locations.

- <b>ImageDetails :</b> This class implements the `GetImageDetails` interface and stores the details
  of the original image in which image processing will be performed.

- <b>Pixel :</b> This interface defines methods for getting details of the original image channel
  such as red, green, and blue.

- <b>ImagePixel :</b> This class implements the `Pixel` interface and stores the details of the
  original image channel in which image processing will be performed.

<br>

<b>This can be used by any class that requires access to the image's details. Additionally, the
code includes exception handling for invalid file types.</b>

### Package `utils`

#### Class `ReadImage` :

- The `ReadImage` class implements the `ImageReadWrite` interface and provides methods for reading
  image details from `PPM`, `PNG`, `JPG`, and `BMP` file formats.
- It has a constructor that takes an image file path and reads the file's details using
  either `readPPM()` or `readImageIO()` depending on the file extension.
- The class also has helper methods such as `getFileExtension()` and `getInputStream()` for
  obtaining the file extension and input stream respectively.
- It throws an `IOException` if there is an error reading the file or if the file format is not
  supported.
- The class uses a `GetImageDetails` object to store the image details and return them when
  required.

#### Class `WriteImage` :

- The `WriteImage` class implements the `ImageReadWrite` interface and provides methods for writing
  image data to a file in various supported formats including `ppm`, `png`, `jpg`, and `bmp`.
- The class contains two private methods: `writePPM()` and `writeImageIO()`, which write the image
  data to the file using different approaches.
- The constructor takes a `GetImageDetails` object containing the image data to be written and the
  file path of the image to be written.
- The class throws an `IOException` if an error occurs while writing the image file.

#### Class `ReadScriptFile` :

- The `ReadScriptFile` class is a utility class that provides functionality to read script files and
  retrieve the script data as a StringReader.
- Constructs a new `ReadScriptFile` object with the specified script path. This constructor throws
  an `IOException` if the file specified by scriptPath cannot be found or read.
- The `ReadScriptFile` class reads the file specified by the given path and removes any comment
  lines that start with the # character. This is done to avoid parsing errors when the script file
  contains comments.

#### Class `CreateHistogram` :

- The `CreateHistogram` class generates and displays a histogram in Java.
- It extends the `JPanel`  class and displays the histogram as a line chart with x and y axes.

#### Class `CustomButton` :

- The `CustomButton` class is a custom implementation of the `JButton` class in Java.
- It provides a custom appearance and behavior to the button, with rounded corners, changed
  appearance when hovered over or clicked, and no border when painted.
- The size of the button is calculated based on the screen size.

#### Class `CustomSpinnerUI` :

- The `CustomSpinnerUI` class is a custom implementation of the `BasicSpinnerUI` class in Java.
- It provides custom user interface elements for the spinner control, such as buttons for
  incrementing and decrementing values.

#### Class `CustomSubButton` :

- CustomSubButton is a class that extends JButton and provides custom appearance and behavior to the
  button which are use in sub operation.
- The button has rounded corners, changes appearance when hovered over or clicked, and does not
  display a border when painted.
- The size of the button is calculated based on the screen size.

## Usage and Completion

-  ### Run the jar file, by double-clicking it. Application GUI will open.

   Initially we will have 2 image shown to user. First the image on which they will perform the
   operation and side-by-side the histogram of the channel of pixel to make user understand the
   pixel value are more normal or right skew or left skew.<br><br>

   There are two operation "Load" and "Save".<br>
    - `Upload` : Upload the image from the system on which user want to perform operation.
    - `Save` : Save the latest change image.

   <br><br>
   There are main eight operation "Flip", "Color Transform", "Filter", "Dither", "Brightness", "
   Split", "Combine", "GrayScale".<br>
    - `Flip` : Have two sub-operation to flip the image "Horizontal" and "Vertical".
    - `Color Transform` : Have two sub-operation to transform the image to "Greyscale" and "sepia".
    - `Filter` : Have two sub-operation to filter the image to "Blur" and "Sharpen".
    - `Dither` : Have direct operation to perform dither to image.
    - `Brightness` : Have two sub-operation to bright or darken the image.
    - `Split` : Have operation to split the image into three component(red, green, and blue).
    - `Combine` : Have operation to upload three image, which get directly combine to one after
      uploading the final image
    - `GrayScale` : Have six sub-operation to greyscale the image "Intensity Component", "Luma
      Component", "Value Component", "Red Component", "Green Component", and "Blue Component".

<br>

- ### Run the script file in command-line
    - open cmd at location : `res` -> `image_processing_application.jar`<br><br>
    - Run the `java -jar image_processing_application.jar -file textFile/submitCommand.txt`
    - Run all the command present in script file `submitCommand.txt` in one-go.

<br>

-  ### Run the script file in jar file
    - Open cmd at that location
    - Run the `java -jar image_processing_application.jar -text`
    - Run the following script file, to run all command in one-go.
      ```
      run textFile/submitCommand.txt
      ```

<hr>

## Justification

<hr>

### Changes made in existing design

- In this assignment, we do not have any major changes. One thing we add in to the last code was the
  implementation of the `ReadOnlyModel` concept of getting the data of the images to make the view
  to access the data directly so that it helps the application be more advance and powerful.
- We have created one getter method in the `ImageOperation` class which share the data
  to `ImageReadModel` so that it can be further forward to view as per the requirement.

### Changes made in existing design of Assignment 5

- #### Services class `ImageDetails`
    - `ImageDetails` class used to have a 3D array to store pixels. Which got a change to a new
      class which help to store the 2D matrix of this class `ImagePixel` and inside that class, it
      stores the red, green, and blue channel values.
    - Which will help us to get directly the value of all 3 channels in one getter method. And also
      make a visualization to get and channel with their name so that it won't create confusion
      which the channel is called is right or not.

-  #### Model class `ImageProcess` - `load` method
    - This class used to have a `load` method which was based on one type of image process such as
      only accepting data that come in form of the scanner, and it was working to read a PPM format
      file.
    - Now as we have to work on the different format of image, we change their parameter to more
      advance by accepting the image details class intense which have all details of any file format
      into the same object type.
    - So, now we can read different file formats like PPM, PNG, JPG, and BMP. And make a new intense
      image details class which have all the required data of each format type which will be
      helpful to model.
    - Moreover, in the future, if we have another format then it will be decoded and saved data into
      this format. Due to this model will no longer have to worry about a data change.

-  #### Model class `ImageProcess` extend Class Name
    - This class was original use to extend the `ImageOperation` class.
    - Now adding a new feature required the same operation in which `ImageOperation` was supported.
    - So, to implement the re-use of code this new `ImageFilter` have to extend that class.
    - So, the `ImageProcess` class have now extended the `ImageFilter` class to make use of all the
      methods used for operating on the image.

-  #### Model class `ImageOperation`
    - This class was updated with minor changes, like making reducing the code line by abstraction
      the common code among their method.

-  #### Controller class `ImageProcessingController`
    - New feature was added to it, like adding more commands in Map such
      as, `blur`, `sharpen`, `sepia`, and `dither`. Already written code was <b>not changed</b> only
      a new
      added feature was added without editing the existing code.

-  #### Controller `tasks` package `RunScript` class
    - Initially, we use to perform a task to read the script text file inside this class. But
      further same read text file implementation was needed in the `Main` class.
    - So, make the serves class `ReadScriptFile` of this read implementation so that the same code
      can be helpful to read both places.

-  #### Controller `tasks` package `Load` class
    - Initially, we use to implement read of file for single file type `PPM`. Which get modified to
      read multi-file types `PNG`, `JPG`, and `BMP`.
    - This required different read implementation, therefore create a new serves class `ReadFile`
      which will take the image path and on the bases of the file extension it will read the image
      automatically and return data in a single type of format.

-  #### Controller `tasks` package `Save` class
    - Initially, we use to implement write of file for single file type `PPM`. Which get modified to
      write multi-file types `PNG`, `JPG`, and `BMP`.
    - This required different write implementation, therefore create a new serves class `WriteFile`
      which will take the image path and on the bases of the file extension it will be written image
      automatically.

<br>

<b>Overall, this change still does not violate the MVC architecture. And this implementation will
help to easily add new features in the future.</b>

- For like, if a new file type has to add then services will be edited on the bases of what read and
  write a type that file required. Neither `Controller` nor `Model` will get affected.
- If `Model` has to add a new feature, then we have to create a new class and that class has to get
  extended by the bases class `ImageProcess`. No more edits need to be done in `Controller`.