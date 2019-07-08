package com.larscheng.practice.abstractDemo;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/5/16 17:16
 */
public class InterfaceVehicleDemo {
    public interface InterfaceVehicle{
        void driver();
        void speed();
    }


    public static class Bicycle implements InterfaceVehicle{

        @Override
        public void driver() {
            System.out.println("自行车");
        }

        @Override
        public void speed() {

        }
    }


    public class Scooter implements InterfaceVehicle{

        @Override
        public void driver() {
            System.out.println("滑板车");
        }

        @Override
        public void speed() {

        }
    }

    public static void main(String[] args) {
        InterfaceVehicle b = new Bicycle();
        b.driver();
    }
}
