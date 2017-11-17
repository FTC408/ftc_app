package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Red", group = "Auto")
public class autoRed extends robot
{
    public void runOpMode() throws InterruptedException
    {
        init(0);
        waitForStart();

        forward(0.5,711);
        forward(0);
        sleep(500);

        strafe(-0.5, 800);
        forward(0);
        sleep(500);

        forward(0.5, 160);
        forward(0);
        sleep(500);

        placeBlock();

        forward(-0.5, 100);
        forward(0);
        sleep(1000);
        forward(0.5, 160);
        forward(0);


    }
}

