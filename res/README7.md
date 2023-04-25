# Photo Editor

This README contains the information about how we added the Mosaic operation in the provider 
code. It contains all the changes we have done to provider's code.

## Model

New classes added:

- `ImageMosaic`: Created this interface to add mosaic operation on image. It extends the 
  ImageFiltersModal and hence contains all other functionality.
- `ImageMosaicImpl`: This class implements the ImageMosaic interface and contains the 
  implementation details for the mosaicking operation.

Changes in old classes:

- `ImageModal`: This interface now extends the newly created `ImageMosaic` interface instead 
  of the ImageFilters interface. This has been done since the controller uses the ImageModal 
  everywhere and making the ImageMosaic extend the ImageModal would have made us replace code at 
  every place which uses ImageModal to now use ImageMosaic. This change would have to be done 
  everytime new functionality is added and a new interface extends ImageModal. Hence, by using 
  this approach, anytime a new interface is added with a newer functionality it is attached into 
  the class hierarchy above ImageModal and ImageModal extends it. This way, all the controller 
  code that uses the ImageModal will be intact.

## Controller

New classes added:

- `MosaicController`: Added this interface which contains the signature for the mosaic operation 
  on an image. It extends the `IController` interface.
- `MosaicControllerImpl`: This class implements the `MosaicController` interface and implements 
  the functionality to perform the mosaic operation. Instead of extending the `Controller` class 
  and inheriting its methods, this class acts as a decorator around the `Controller`, decorating 
  it with the added functionality of mosaicking. For all other functionality, it simply 
  delegates it to the decorated `Controller` class.
- `Mosaic`: A new class was created in the `tasks` package to represent the mosaic task.

Changes in old classes:

- `IController`: Added performAction method which was previously package-private in the 
  `Controller`. This is because this method needs to be called from the `MosaicControllerImpl`. This 
  could have been avoided by making `MosaicControllerImpl` decorate a `Controller` instead of 
  `IController`, but decorating an interface type would be better in the long run as the same 
  decorator can be used to decorate any implementation of `IController`.
- `ImageProcessingController`: Added mosaic command to the collection of `knownCommands`.

## View

Changes in old classes:

- `JFrameView`: Added Mosaic button to the UI and also added a new spinner to enter the number of 
  seeds. Added an actionListener to inform the controller when the mosaic button is clicked.

## Main class

- `Main`: In main class, the `Controller` is now decorated by the `MosaicControllerImpl`.
