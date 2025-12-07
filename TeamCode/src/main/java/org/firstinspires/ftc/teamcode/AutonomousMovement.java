package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutonomousMovement {
	private final Telemetry telemetry;
	private final Limelight3A limelight;
	private final RevColorSensorV3 colorsense;
	private final DcMotor fl0;
	private final DcMotor fr1;
	private final DcMotor bl2;
	private final DcMotor br3;
	private final DcMotor shootmotor;
	private final ElapsedTime runtime = new ElapsedTime();
	private final Movement movementHelper = new Movement();
	private final Module moduleHelper = new Module();
	private final List<Integer> fiducialIds = new ArrayList<>();
	private boolean isLimelightStarted;
	
	public AutonomousMovement(HardwareMap hardwareMap, Telemetry telemetry) {
		this.telemetry = telemetry;
		this.limelight = hardwareMap.get(Limelight3A.class, "ll3a");
		this.colorsense = hardwareMap.get(RevColorSensorV3.class, "colorsense");
		this.fl0 = hardwareMap.get(DcMotor.class, "fl0");
		this.fr1 = hardwareMap.get(DcMotor.class, "fr1");
		this.bl2 = hardwareMap.get(DcMotor.class, "bl2");
		this.br3 = hardwareMap.get(DcMotor.class, "br3");
		this.shootmotor = hardwareMap.get(DcMotor.class, "shootmotor");
	}
	
	public Movement movement() {
		return movementHelper;
	}
	
	public Module module() {
		return moduleHelper;
	}
	
	public Limelight3A limelightDevice() {
		return limelight;
	}
	
	public void startLimelight() {
		if (!isLimelightStarted) {
			limelight.start();
			isLimelightStarted = true;
		}
	}
	
	public void stopLimelight() {
		if (isLimelightStarted) {
			limelight.stop();
			isLimelightStarted = false;
		}
	}
	
	public void runtimeRemaining(long timeMs) {
		telemetry.addData("Runtime remaining: ", (timeMs - runtime.milliseconds()));
		telemetry.update();
	}
	
	public class Movement {
		// "and when im rock hard i ******* rock hard" -ken ashcorp
		public void drive(double speed, long timeMs) {
			fl0.setPower(speed);
			fr1.setPower(speed);
			bl2.setPower(speed);
			br3.setPower(speed);
			runtime.reset();
			while (runtime.milliseconds() < timeMs) {
				runtimeRemaining(timeMs);
			}
			stop();
		}
		
		public void slide(double speed, long timeMs) {
			fl0.setPower(speed);
			fr1.setPower(-speed);
			bl2.setPower(-speed);
			br3.setPower(speed);
			runtime.reset();
			while (runtime.milliseconds() < timeMs) {
				runtimeRemaining(timeMs);
			}
			stop();
		}
		
		public void rotation(double speed, long timeMs) {
			fl0.setPower(speed);
			fr1.setPower(-speed);
			bl2.setPower(speed);
			br3.setPower(-speed);
			runtime.reset();
			while (runtime.milliseconds() < timeMs) {
				runtimeRemaining(timeMs);
			}
			stop();
		}
		
		public void stop() {
			fl0.setPower(0);
			fr1.setPower(0);
			bl2.setPower(0);
			br3.setPower(0);
		}
	}
	
	public class Module {
		// "tickle my fancy tickle my prostate" -ken ashcorp
		private final Limelight limelightHelper = new Limelight();
		
		public void shooter(double speed, long timeMs) {
			shootmotor.setPower(speed);
			runtime.reset();
			while (runtime.milliseconds() < timeMs) {
				runtimeRemaining(timeMs);
			}
			shootmotor.setPower(0);
		}
		
		public class Colorsense {
			
			public DetectedColor detectColor(NormalizedRGBA color) {
				float red = color.red;
				float green = color.green;
				float blue = color.blue;
				
				final float MIN_INTENSITY = 0.2f;
				final float GREEN_RATIO_DIFF = 0.25f;
				final float PURPLE_RATIO_DIFF = 0.2f;
				final float MAX_DIFFERENCE = 0.2f;
				
				if (red < MIN_INTENSITY && green < MIN_INTENSITY && blue < MIN_INTENSITY) {
					return DetectedColor.UNKNOWN;
				}
				
				if (green > red + GREEN_RATIO_DIFF && green > blue + GREEN_RATIO_DIFF) {
					return DetectedColor.GREEN;
				}
				
				if (Math.abs(red - blue) <= PURPLE_RATIO_DIFF && green <= MAX_DIFFERENCE) {
					return DetectedColor.PURPLE;
				}
				
				return DetectedColor.UNKNOWN;
			}
			
			public NormalizedRGBA getColor() {
				if (!colorsense.isLightOn()) {
					colorsense.enableLed(true);
				}
				
				return colorsense.getNormalizedColors();
			}
			
		}
		
		public Limelight limelight() {
			return limelightHelper;
		}
		
		public class Limelight {
			public List<Integer> getResults() {
				fiducialIds.clear();
				LLResult result = limelight.getLatestResult();
				if (result != null && result.isValid()) {
					List<LLResultTypes.FiducialResult> detectedTags = result.getFiducialResults();
					for (LLResultTypes.FiducialResult tag : detectedTags) {
						fiducialIds.add(tag.getFiducialId());
					}
				}
				return Collections.unmodifiableList(new ArrayList<>(fiducialIds));
			}
			
			public List<Integer> getMonolithOrientation(List<Integer> detectedTags) {
				LLResult result = limelight.getLatestResult();
				List<Integer> sequence = Collections.emptyList();
				if (result != null && result.isValid()) {
					for (Integer tag : detectedTags) {
						if (tag == 21) {
							sequence.add(1);
							sequence.add(0);
							sequence.add(0);
							return sequence;
						} else if (tag == 22) {
							sequence.add(0);
							sequence.add(1);
							sequence.add(0);
							return sequence;
						} else if (tag == 23) {
							sequence.add(0);
							sequence.add(0);
							sequence.add(1);
							return sequence;
						}
					}
				}
				return null;
			}
		}
	}
	
	public enum DetectedColor {
		GREEN,
		PURPLE,
		UNKNOWN
	}
	
}