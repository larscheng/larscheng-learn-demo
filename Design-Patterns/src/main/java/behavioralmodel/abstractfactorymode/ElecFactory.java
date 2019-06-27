package behavioralmodel.abstractfactorymode;

import behavioralmodel.simplefactorymode.ElectricVehicle;
import behavioralmodel.simplefactorymode.Vehicle;

/**
 * 描述:
 * 电动车厂
 *
 * @author zhengql
 * @date 2019/6/26 11:20
 */
public class ElecFactory implements AbstractFactory {
    public Vehicle buyVehicle() {
        return new ElectricVehicle();
    }

    public Fitting buyFitting() {
        return new ElecFitting();
    }
}
