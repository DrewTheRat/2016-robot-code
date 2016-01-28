package org.usfirst.frc.team1089.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The {@code ControllerBase} class handles all input coming from the 
 * gamepad, left joystick, and right joystick. This has various methods
 * to get input, buttons, etc.
 */
public class ControllerBase {
	private final Joystick GAMEPAD, LEFT_STICK, RIGHT_STICK;
	private boolean[] pressed;
	private final double DEAD_ZONE = 0.1;
	
	/**
	 * The {@code GamepadButtons} class contains all the button bindings for the
	 * Gamepad.
	 */
	public static class GamepadButtons {

		public static final int
			A = 1,
			B = 2,
			X = 3,
			Y = 4,
			L2 = 5,
			R2 = 6,
			BACK = 7,
			START = 8,
			L3 = 9,
			R3 = 10;
		/**
		 * <pre>
		 * private GamepadButtons()
		 * </pre>
		 * 
		 * Unused constructor.
		 */
		private GamepadButtons() {
			
		}
	}
	
	/**
	 * The {@code Joysticks} enum contains 
	 * namespaces for the gamepad, left joystick, and right joystick
	 */
	public enum Joysticks {
		GAMEPAD,
		LEFT_STICK,
		RIGHT_STICK
	}
	
	public ControllerBase(int gp, int l, int r) {
		GAMEPAD = new Joystick(gp);
		LEFT_STICK = new Joystick(l);
		RIGHT_STICK = new Joystick(r);
		pressed = new boolean[11];
	}
	
	public void update() {
		for (int i = 0; i < pressed.length; i++) {
			pressed[i] = GAMEPAD.getRawButton(i);
		}
	}
	
	public boolean getPressedDown(int b) {
		return GAMEPAD.getRawButton(b) && !pressed[b];
	}
	

	/**
	 * <pre>
	 * public boolean deadzone(Joysticks j)
	 * </pre>
	 * Returns true or false
	 * 
	 * @param j the joystick to get the axis value from
	 * @return true if axis is greater than deadzone, false otherwise
	 */
	public boolean deadzone(Joystick j, int axis){
		if(Math.abs(j.getRawAxis(axis)) <= 0.1)
			return false;
		else
			return true;
	}
}