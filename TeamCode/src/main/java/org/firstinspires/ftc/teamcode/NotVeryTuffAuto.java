package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "NotVeryTuffAuto")
public abstract class NotVeryTuffAuto extends OpMode {
	private DcMotor fl0;
	private DcMotor fr1;
	private DcMotor bl2;
	private DcMotor br3;
	private static final ElapsedTime runtime = new ElapsedTime();
	@Override
	public void init() {
		fl0 = hardwareMap.get(DcMotor.class, "fl0");
		fr1 = hardwareMap.get(DcMotor.class, "fr1");
		bl2 = hardwareMap.get(DcMotor.class, "bl2");
		br3 = hardwareMap.get(DcMotor.class, "br3");
	}
	
	@Override
	public void start() {
		fl0.setPower(0.5);
		fr1.setPower(0.5);
		bl2.setPower(0.5);
		br3.setPower(0.5);
		runtime.reset();
		while (runtime.seconds() < 1500) {/* tuff enuf */}
		fl0.setPower(0);
		fr1.setPower(0);
		bl2.setPower(0);
		br3.setPower(0);
	}
}
