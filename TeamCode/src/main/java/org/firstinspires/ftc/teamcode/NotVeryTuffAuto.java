package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "NotVeryTuffAuto")
public class NotVeryTuffAuto extends LinearOpMode {
	private DcMotor fl0;
	private DcMotor fr1;
	private DcMotor bl2;
	private DcMotor br3;
	private static final ElapsedTime runtime = new ElapsedTime();
	@Override
	public void runOpMode() {
		fl0 = hardwareMap.get(DcMotor.class, "fl0");
		fr1 = hardwareMap.get(DcMotor.class, "fr1");
		bl2 = hardwareMap.get(DcMotor.class, "bl2");
		br3 = hardwareMap.get(DcMotor.class, "br3");
		waitForStart();
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
