package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Collections;
import java.util.List;

@Autonomous(name = "LimelightAuto")
public abstract class AutonomousMovement extends OpMode {
	private static Limelight3A ll3a;
	private static DcMotor fl0;
	private static DcMotor fr1;
	private static DcMotor bl2;
	private static DcMotor br3;
	private static DcMotor shootmotor;
	private static final ElapsedTime runtime = new ElapsedTime();
	private boolean isLimelightStarted = false;
	static List<Object> FiducialIds = Collections.emptyList();
	static class Movement {
		/** 'tickle my fancy tickle my prostate' -ken ashcorp */
		public void drive(double speed, long time) {
			fl0.setPower(speed);
			fr1.setPower(speed);
			bl2.setPower(speed);
			br3.setPower(speed);
			runtime.reset();
			while (runtime.seconds() < time) {/* tuff enuf */}
			stop();
		}
		
		public void slide(double speed, long time) {
			fl0.setPower(speed);
			fr1.setPower(-speed);
			bl2.setPower(-speed);
			br3.setPower(speed);
			runtime.reset();
			while (runtime.seconds() < time) {}
			stop();
		}
		
		public void rotation(double speed, long time) {
			fl0.setPower(speed);
			fr1.setPower(-speed);
			bl2.setPower(speed);
			br3.setPower(-speed);
			runtime.reset();
			while (runtime.seconds() < time) {}
			stop();
		}
		
		public void stop() {
			fl0.setPower(0);
			fr1.setPower(0);
			bl2.setPower(0);
			br3.setPower(0);
		}
	}
	
	static class Module {
		/** 'and when im rock hard i ******* rock hard' -ken ashcorp */
		public void shooter(double speed, long time) {
			shootmotor.setPower(speed);
			runtime.reset();
			while (runtime.seconds() < time) {}
			shootmotor.setPower(0);
		}
		
		public static class Limelight {
			public List<Object> getResults() {
				FiducialIds.clear();
				LLResult result = ll3a.getLatestResult();
				if (result != null && result.isValid()) {
					List<LLResultTypes.FiducialResult> detectedTags = result.getFiducialResults();
					for (LLResultTypes.FiducialResult tag : detectedTags) {
						FiducialIds.add(tag.getFiducialId());
					}
				} else {
					return FiducialIds;
				}
				return FiducialIds;
			}
			
			public String getMonolithOrientation(List<Object> detectedTags) {
				LLResult result = ll3a.getLatestResult();
				if (result != null && result.isValid()) {
					for (Object tag : detectedTags) {
						if (tag.equals(21)) {
							return "GREEN PURPLE PURPLE";
						}
						else if (tag.equals(22)) {
							return "PURPLE GREEN PURPLE";
						}
						else if (tag.equals(23)) {
							return "PURPLE PURPLE GREEN";
						}
						else {
							return null;
						}
					}
				}
				return null;
			}
		}
	}
}