package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Robotics on 1/24/2018.
 */

@Autonomous(name = "Encoders", group = "auto")
@Disabled
public class encoderTest extends robot
{
    public void runOpMode()
    {
        init(0);
        waitForStart();

        /*forward(0.2, 100);
        sleep(500);
        strafe(0.2, 100);
        sleep(500);
        strafe(-0.2, 100);
        sleep(500);
        forward(-0.2, 100);
        sleep(500);
        */
        turn(0.5, -360);//Left 360
        sleep(1000);
        turn(0.5, 360);//Right 360
        sleep(1000);

       /* jewel.setPosition(downPosition);
        sleep(1000);
        jewelSwivel.setPosition(rightPosition);
        sleep(1000);
        jewelSwivel.setPosition(leftPosition);
        sleep(1000);
        jewelSwivel.setPosition(straightPosition);
        sleep(1000);
        jewel.setPosition(upPosition);
        sleep(1000);*/

    }

}
