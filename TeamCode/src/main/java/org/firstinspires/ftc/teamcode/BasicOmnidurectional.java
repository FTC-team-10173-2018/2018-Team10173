/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic Omnidurecional", group="Iterative Opmode")

public class BasicOmnidurectional extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    //private DcMotor backLeftDrive = null;
    //private DcMotor backRightDrive = null;
    private double power;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        //backLeftDrive  = hardwareMap.get(DcMotor.class, "back_left_drive");
        //backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        //frontRightDrive.setDirection(DcMotor.Direction.FORWARD);    NEED TO FIX
        //frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);       NEED TO FIX
        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double frontleftPower = 0;
        double frontrightPower = 0;
        //double backleftPower = 0;
        //double backrightPower = 0;

        if ((gamepad1.dpad_down||gamepad1.dpad_left||gamepad1.dpad_up||gamepad1.dpad_right)== true){
            if (gamepad1.dpad_up){
                frontleftPower=1;
                frontrightPower = -1;}
                //backleftPower = -1;
                //backrightPower = 1;}

            if (gamepad1.dpad_down){
                frontleftPower=-1;
                frontrightPower = 1;
                //backleftPower = 1;
               //backrightPower = -1;}

            if (gamepad1.dpad_left){
                frontleftPower=-1;
                frontrightPower = -1;}
                //backleftPower = 1;
                //backrightPower = 1;}

            if (gamepad1.dpad_left){
                frontleftPower=1;
                frontrightPower = 1;}
                //backleftPower = -1;
                //backrightPower = -1;

            else
                if (gamepad1.right_trigger >=.75)
                {
                    frontleftPower=1;
                    frontrightPower = 1;}
                    //backleftPower = 1;
                    //backrightPower = 1;}

                if (gamepad1.left_trigger >=.75){
                    frontleftPower=-1;
                    frontrightPower = -1;}
                    //backleftPower = -1;
                    //backrightPower = -1;
                else
                    frontleftPower=0;
                frontrightPower = 0;
                //backleftPower = 0;
                //backrightPower = 0;





            // Send calculated power to wheels
        frontLeftDrive.setPower(frontleftPower);
       frontRightDrive.setPower(frontrightPower);
        //backRightDrive.setPower(backrightPower);
        //backLeftDrive.setPower(backleftPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "x" + gamepad1.right_stick_x);
        telemetry.addData("Motors", "y" + gamepad1.right_stick_y);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */


}}}
