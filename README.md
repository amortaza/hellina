## Hellina

Hellina is a Java library for use as a launching pad for desktop applications that use Mouse, Keyboard, and Graphics.  It takes care of the boilder plate code to setup graphics and device input/output event handlers.

The name is inspired by `HAL - Hardware abstraction Layer`

## How to Use

See hellina/src/sample/Run.java on how to use.

## Design

For brevity, all package names are relative to `com.github.amortaza.hellina`.

Hellina is basically a set of interface definitions of how your application will interact with the underlying hardware.

These interface definitions are in the `intf` and `listener` packages.

`Hellina` is a set of interfaces to be implemented.  The implementation of the interfaces depend on the underlying hardware / software platform.


A default Java2D implementation is provided - `com.github.amortaza.hellina_j2d`.  But future implementations could have used OpenGL and be written in native C, for example.

## Listeners

Listeners are hooks for things such as interaction with various devices.  Devices such as *mouse*, *keyboard*, and *graphics*.  Future listeners for networking and audio are planned.

#### Tick Listener: ITickListener

	onTick()
`onTick` gets called by the framework once each frame.  Put code that needs to execute between frames here.

	afterInit(IPlatform)
`afterInit()` gets called after the framework has been initialized.  Put your init code here.  The `IPlatform` object will allow access to relevent information about the environment that was just initialized.


#### Mouse Listener: IMouseListener

The `x` and `y` input parameters are the coordinates of the mouse cursor in window space.

See source code.

**Scroll Wheel**

*Special Note: right-down means right-button was pressed down, but scroll down does NOT mean scroll button was pressed down*

*It means the scroll wheel was ROLLED down (similarly for scroll up)*

	void onScrollDown( int x, int y );
	void onScrollUp( int x, int y );
 

#### Key Listener: IKeyListener

See source code.

#### Window Listener: IWindowListener

See source code.

## Interfaces

### IDraw

*Note: a default Java 2D implementation is already included.  You do not need do anything with this class.  Only developers who want to implement this framework on something besides Java 2D, need to implement this class.*

By implementing this class, you can determine how basic drawing primitives (e.g. lines, rectangles, images) are drawn.

See source code.

### IPlatform

*Note: a default Java 2D implementation is already included.  You do not need do anything with this class.  Only developers who want to implement this framework on something besides Java 2D, need to implement this class.*

By implementing this class, you can determine how the framework is instantiated and graphics and IO devices setup, etc.

The IPlatform object also has hooks to get states of the current Key / Mouse / Graphics device.

See source code.

## Devices

Devices are hooks so programs can communicate with the underlying platform's devices.  Currently devices are defined for Mouse, Keyboard, and Graphics device.  Future plans include Audio and Network devices.

### IMouseDevice

See source code.  

### IKeyDevice

See source code.  