package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Robotics on 1/24/2018.
 */

@Autonomous(name = "Encoders", group = "auto")
public class encoderTest extends robot
{
    public void runOpMode()
    {
        init(0);
        waitForStart();

       /*forward(0.2, 1000);
        sleep(500);
        strafe(0.2, 500);
        sleep(500);
        strafe(-0.2, 500);
        sleep(500);
        forward(-0.2, 1000);
        sleep(500);*/
        turn(0.5, -360);
        sleep(1000);
        turn(0.5, 360);

    }

}
