package org.usfirst.frc.team1089.auton;

import org.usfirst.frc.team1089.robot.DriveTrain;
import org.usfirst.frc.team1089.robot.Logger;
import org.usfirst.frc.team1089.robot.Shooter;

public class Defense{
	private DefenseEnum defenseEnum;
	private DriveTrain drive;
	private Shooter shooter;
	
	public Defense(DriveTrain d, Shooter s, DefenseEnum dE) {
		drive = d;
		defenseEnum = dE;
		shooter  = s;
	}
	
	public void breach() {

		// Movement formulas based on the following:
		// 	Approach	: the robot is 38-inch long with the bumper. With 2 inches above the line we are 3 feet away from defense.
		//	Through		: defenses are 4 feet by definition
		//	Slip		: defense-specific addition to account for slippage (e.g. on Rock Wall)
		//	After		: Distance to clear defense
		//	Buffer		: Extra move to be certain we clear the defense.
		
		
		switch (defenseEnum) {
			case LOW_BAR: {
				Logger.log("Defense LOW_BAR in");
				// Lower the shooter for the LOW BAR
				shooter.raise(Shooter.DOWN);

				drive.moveDistance(
						12,		// 12 = 3 (Approach) + 4 (Through) + 3 (After) + 2 (Buffer)
						0.4,
						0,
						0,
						4.5);	// Keep the speed slow while crossing - 4.5
				drive.waitMove();

				Logger.log("Defense LOW_BAR out");
				break;
			}
			case MOAT: {
				Logger.log("Defense MOAT in");
				// Shooter should be up and ready to fire long (aka Medium)
				shooter.raise(Shooter.MEDIUM);

				//TODO test and change ALL these values
				drive.moveDistance(
						12,		// 12 = 3 (Approach) + 4 (Through) + 3 (After) + 2 (Buffer)
						0.4,
						0,
						0,
						12.0);	//full speed on the moad
				drive.waitMove();

				Logger.log("Defense MOAT out");
				break;
			}
			case ROUGH_TERRAIN: {
				Logger.log("Defense ROUGH_TERRAIN in");
				// Shooter should be up and ready to fire long (aka Medium)
				shooter.raise(Shooter.MEDIUM);

				//TODO test and change ALL these values
				drive.moveDistance(
						14,		// 14 = 3 (Approach) + 6+2 (Through+Slip) + 3 (After) + 2 (Buffer)
						0.4,
						0,
						0,
						8.0);	//need fast PID voltage - to be changed
				drive.waitMove();

				Logger.log("Defense ROUGH_TERRAIN out");
				break;
			}
			case RAMPARTS: {
				Logger.log("Defense RAMPARTS in");
				// Shooter should be up and ready to fire long (aka Medium)
				shooter.raise(Shooter.MEDIUM);

				drive.moveDistance(
						12,		// 12 = 3 (Approach) + 4 (Through) + 3 (After) + 2 (Buffer)
						0.4,
						0,
						0,
						6.0);	//human speed
				drive.waitMove();
				
				Logger.log("Defense RAMPARTS out");
				break;
			}
			case ROCK_WALL: {
				Logger.log("Defense ROCK_WALL in");
				// Shooter should be up and ready to fire long (aka Medium)
				shooter.raise(Shooter.MEDIUM);

				drive.moveDistance(
						14,		// 14 = 3 (Approach) + 6+2 (Through+Slip) + 3 (After) + 2 (Buffer)
						0.4,
						0,
						0,
						8.0);	// full speed
				drive.waitMove();

				Logger.log("Defense ROCK_WALL out");
				break;
			}
			case CHEVAL_DE_FRISE: {
				Logger.log("Defense CHEVAL_DE_FRISE in");
				// Shooter should be up but read to lower on CDF (i.e. Medium)
				shooter.raise(Shooter.MEDIUM);

				drive.moveDistance(
						3,		// 3 = Distance to CDF
						0.4,
						0,
						0,
						4.5);	// Approach CDF slowly.
				drive.waitMove();

				// Lower shooter ON CDF
				shooter.raise(Shooter.DOWN);
				
				drive.moveDistance(
						9,		// 9 = 4 (Through CDF) + 3 (After) + 2 (Buffer)
						0.4,
						0,
						0,
						4.5);  // Proceed through CDF slowly
				drive.waitMove();

				Logger.log("Defense CHEVAL_DE_FRISE out");
				break;
			}
			case PORTCULLIS: {
				Logger.log("Defense PORTCULLIS in");
				shooter.raise(Shooter.DOWN);
				drive.moveDistance(
						3,		// 3 = Distance to CDF
						0.4,
						0,
						0,
						4.5);		// Approach Portcullis slowly.
				drive.waitMove();
				//OPEN PORTCULLIS with Shooter
				shooter.raise(Shooter.MEDIUM); // TODO: MEDIUM or HIGH?
				
				drive.moveDistance(
						9,		// 9 = 4 (Through Portcullis) + 3 (After) + 2 (Buffer)
						0.4,
						0,
						0,
						4.5);	// TODO: Adjust speed
				drive.waitMove();
				Logger.log("Defense PORTCULLIS out");
				break;
			}
			default:
				break;
		}
	}
}
