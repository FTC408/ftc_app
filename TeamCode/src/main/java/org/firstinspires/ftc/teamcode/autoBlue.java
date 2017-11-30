package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Blue", group = "Auto")
@Disabled
public class autoBlue extends robot
{

    public void runOpMode() throws InterruptedException
    {
        init(0);
        waitForStart();

        jewel(false);

        forward(0.5, 711);
        forward(0, 0);
        sleep(500);

        strafe(0.5, 330);
        forward(0, 0);
        sleep(500);

        forward(0.5, 432);
        forward(0, 0);
        sleep(500);

        placeBlock();

        forward(0.5, 100);
        forward(0);
        sleep(1000);
        forward(-0.5, 160);
        forward(0);
        sleep(1000);
        forward(0.5, 100);
        forward(0);
    }
}

