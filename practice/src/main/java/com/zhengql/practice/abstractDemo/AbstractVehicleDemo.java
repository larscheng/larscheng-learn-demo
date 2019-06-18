package com.zhengql.practice.abstractDemo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/5/16 14:08
 */
public class AbstractVehicleDemo{

    public static abstract class AbstractVehicle {
        public abstract void speed();

        public AbstractVehicle() {
            System.out.println("实例化");
        }

        public void driver() {
            System.out.println("车辆可以开....");
        }
    }

    public interface Fly{
        void fly();
    }


    public static class Bicycle extends AbstractVehicle{
        @Override
        public void speed() {
            System.out.println("60");
        }
    }

    public static class Soccter extends AbstractVehicle{
        @Override
        public void speed() {
            System.out.println("50");
        }
    }

    public static class zhishengji extends AbstractVehicle implements Fly{

        @Override
        public void speed() {
            System.out.println("100");
        }

        @Override
        public void fly() {
            System.out.println("还可以飞");
        }
    }
    public static void main(String[] args) {
        AbstractVehicle bicycle = new Bicycle();
        AbstractVehicle soccter = new Soccter();
        bicycle.speed();

        List<String> list = Collections.synchronizedList(new ArrayList<>());
        soccter.speed();
    }
}
