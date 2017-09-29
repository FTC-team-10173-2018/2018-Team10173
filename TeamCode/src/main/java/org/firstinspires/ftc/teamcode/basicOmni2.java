/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="basic Omni 2      ", group="Linear Opmode")  // @Autonomous(...) is the other common choice
public class basicOmni2 extends LinearOpMode {

    /* Declare OpMode members. */
    private final ElapsedTime runtime = new ElapsedTime();
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    DcMotor backleftMotor;
    DcMotor intake;
    DcMotor Shooter1;
    DcMotor Shooter2;

    @Override
    public void runOpMode() {
        this.telemetry.addData("Status", "Initialized");
        this.telemetry.update();


        this.frontLeftMotor = this.hardwareMap.dcMotor.get("FL");
        this.frontRightMotor = this.hardwareMap.dcMotor.get("FR");
        this.backleftMotor = this.hardwareMap.dcMotor.get("BL");
        this.backRightMotor = this.hardwareMap.dcMotor.get("BR");
        this.intake = this.hardwareMap.dcMotor.get("Intake");
        this.Shooter1 = this.hardwareMap.dcMotor.get("Shooter1");
        this.Shooter2 = this.hardwareMap.dcMotor.get("Shooter2");

                 // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        this.frontLeftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        this.frontRightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        this.backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        this.backleftMotor.setDirection(DcMotor.Direction.FORWARD);
        this.intake.setDirection(DcMotor.Direction.FORWARD);
        this.Shooter1.setDirection(DcMotor.Direction.FORWARD);
        this.Shooter2.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        this.waitForStart();
        this.runtime.reset();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double frontleftPower = 0;
            double frontrightPower = 0;
            double backleftPower = 0;
            double backrightPower = 0;


            this.telemetry.addData("Status", "Run Time: " + this.runtime);
            this.telemetry.update();


            if (this.gamepad1.dpad_down || this.gamepad1.dpad_left || this.gamepad1.dpad_up || this.gamepad1.dpad_right == true) {
                if (this.gamepad1.dpad_up) {
                    frontleftPower = 1;
                    frontrightPower = -1;
                    backleftPower = -1;
                    backrightPower = 1;
                } else if (this.gamepad1.dpad_down) {
                    frontleftPower = -1;
                    frontrightPower = 1;
                    backleftPower = 1;
                    backrightPower = -1;
                } else if (this.gamepad1.dpad_right) {
                    frontleftPower = -1;
                    frontrightPower = -1;
                    backleftPower = 1;
                    backrightPower = 1;
                } else {
                    frontleftPower = 1;
                    frontrightPower = 1;
                    backleftPower = -1;
                    backrightPower = -1;
                }}
            else if (this.gamepad1.right_trigger >= .75) {
                    frontleftPower = 1;
                    frontrightPower = 1;
                    backleftPower = 1;
                    backrightPower = 1;}
                else if (this.gamepad1.left_trigger >= .75) {
                    frontleftPower = -1;
                    frontrightPower = -1;
                    backleftPower = -1;
                    backrightPower = -1;}
                else{
                    frontleftPower = 0;

                    frontrightPower = 0;
                    backleftPower = 0;
                    backrightPower = 0;
                }




            if (this.gamepad1.a){
              this.intake.setPower(1);
              }
              else {
                this.intake.setPower(0);
            }


            if (this.gamepad1.x)
                {Shooter1.setPower(.5);}

            else
                {Shooter1.setPower(0);}


            if (gamepad1.y){
                Shooter2.setPower(.5);}


            else{
                Shooter2.setPower(0);}



                this.frontLeftMotor.setPower(frontleftPower);
            this.frontRightMotor.setPower(frontrightPower);
            this.backRightMotor.setPower(backrightPower);
            this.backleftMotor.setPower(backleftPower);
        }}


        }



