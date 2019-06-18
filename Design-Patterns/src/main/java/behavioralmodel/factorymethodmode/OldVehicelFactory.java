package behavioralmodel.factorymethodmode;

import behavioralmodel.simplefactorymode.ElectricVehicle;
import behavioralmodel.simplefactorymode.PowerVehicle;
import behavioralmodel.simplefactorymode.Vehicle;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/6/18 16:46
 */
public class OldVehicelFactory implements VehicleCenterCompany {
    public Vehicle buyVehicle(int type) {
        Vehicle vehicle = null;
        if (type == 1) {
            vehicle = new PowerVehicle();
        }else if (type==2){
            vehicle = new ElectricVehicle();
        }
        vehicle.info();
        return vehicle;
    }
}
