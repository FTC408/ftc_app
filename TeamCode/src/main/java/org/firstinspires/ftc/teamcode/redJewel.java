package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Red Jewel", group = "Auto")
public class redJewel extends robot
{

    public void runOpMode() throws InterruptedException
    {
        init(0);
        waitForStart();

        jewel(true);
    }
}

