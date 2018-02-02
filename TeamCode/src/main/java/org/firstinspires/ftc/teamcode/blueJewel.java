package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Blue Jewel", group = "Auto")
@Disabled
public class blueJewel extends robot
{

    public void runOpMode() throws InterruptedException
    {
        init(0);
        waitForStart();

        jewel(false);


    }
}

