package org.usfirst.frc.team1089.auton;

import org.usfirst.frc.team1089.robot.DriveTrain;
import org.usfirst.frc.team1089.robot.Shooter;

public class Defense{
	private DefenseEnum defenseEnum;
	private DriveTrain drive;
	private Shooter shooter;
	
	private static final int 
					BEFORE_DEFENSE_FEET = 4, THROUGH_DEFENSE_FEET = 4,
					INITIAL_CDF_FEET = 4, REMAINING_CDF_FEET = 4,
					AFTER_DEFENSE_FEET = 3 ;
	
	public Defense(DriveTrain d, Shooter s, DefenseEnum dE) {
		drive = d;
		defenseEnum = dE;
		shooter  = s;
	}
	
	public void breach() {
		switch (defenseEnum) {
			case LOW_BAR: {
				shooter.raise(Shooter.DOWN);
				drive.moveDistanceAuton(BEFORE_DEFENSE_FEET + THROUGH_DEFENSE_FEET + AFTER_DEFENSE_FEET, 0.4, 0, 0, 4.5);
				drive.waitMove(); // moveDistance is an asynchronous operation - we need to wait until it is done
				break;
			}
			case MOAT: {
				drive.moveDistanceAuton(BEFORE_DEFENSE_FEET + THROUGH_DEFENSE_FEET + AFTER_DEFENSE_FEET, 0.4, 0, 0, 6.0); //TODO test and change ALL these values
				drive.waitMove();		//need moderate PID voltage - to be changed
				break;
			}
			case ROUGH_TERRAIN: {
				drive.moveDistanceAuton(BEFORE_DEFENSE_FEET + THROUGH_DEFENSE_FEET + AFTER_DEFENSE_FEET, 0.4, 0, 0, 8.0); 
				drive.waitMove();		//need fast PID voltage - to be changed
				break;
			}
			case RAMPARTS: {
				drive.moveDistanceAuton(BEFORE_DEFENSE_FEET + THROUGH_DEFENSE_FEET + AFTER_DEFENSE_FEET, 0.4, 0, 0, 4.5); 
				drive.waitMove();
				break;
			}
			case ROCK_WALL: {
				drive.moveDistanceAuton(BEFORE_DEFENSE_FEET + THROUGH_DEFENSE_FEET + AFTER_DEFENSE_FEET, 0.4, 0, 0, 10.0); 
				drive.waitMove();		//need fast PID voltage - to be changed
				break;
			}
			case CHEVAL_DE_FRISE: {
				drive.moveDistance(INITIAL_CDF_FEET);
				drive.waitMove();
				shooter.raise(Shooter.DOWN);
				drive.moveDistanceAuton(REMAINING_CDF_FEET + AFTER_DEFENSE_FEET, 0.4, 0, 0, 4.5); 
				drive.waitMove();
				break;
			}
			case PORTCULLIS: {
				drive.moveDistance(INITIAL_CDF_FEET);
				drive.waitMove();
				//OPEN PORTCULLIS DOOR code
				drive.moveDistanceAuton(REMAINING_CDF_FEET + AFTER_DEFENSE_FEET, 0.4, 0, 0, 4.5);	//change constants 
				drive.waitMove();
				break;
			}
			default:
				break;
		}
	}
}
