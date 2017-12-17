package rest.carRepair.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.carRepair.domain.Member;
import rest.carRepair.domain.Vehicle;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleExistException;
import rest.carRepair.exceptions.vehicle.VehicleNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.carRepair.exceptions.vehicle.VehiclesNotFoundException;
import rest.carRepair.repositories.MemberRepository;
import rest.carRepair.repositories.VehicleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MemberService memberService;

    @Override
    public List<Vehicle> getAllMemberVehicles(Long memberId) throws VehiclesNotFoundException, MemberNotFoundException {
        Member member = memberService.getMemberById(memberId);
        List<Vehicle> memberVehicles = member.getVehicles();
        if(memberVehicles.size() == 0){
            throw new VehiclesNotFoundException("There are not found vehicle for user with id " + memberId);
        }
        return memberVehicles;
    }

    @Override
    public Vehicle getMemberVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException {
        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        if(vehicle == null){
            throw new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found");
        }

        if(!(vehicle.getMember().getUserId() == memberId)){
            throw new VehicleNotReferredToUserException("Vehicle with id " + vehicleId + " is not referred to user with id " + memberId);
        }
        return vehicle;
    }

    @Override
    public Vehicle saveVehicle(Long memberId, Vehicle vehicle) throws VehicleExistException, MemberNotFoundException {
        Member member = memberService.getMemberById(memberId);
        Vehicle newVehicle;
        try {
            vehicle.setMember(member);
            newVehicle = vehicleRepository.save(vehicle);
        } catch (Exception e){
            throw new VehicleExistException("Vehicle with plate " + vehicle.getPlate() + " already exists");
        }
        return newVehicle;
    }

    @Override
    public Vehicle updateVehicle(Long memberId, Long vehicleId, Vehicle vehicle) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException {
        Vehicle memberVehicle = getMemberVehicle(memberId, vehicleId);
        vehicle.setVehicleId(vehicleId);
        vehicle.setMember(memberVehicle.getMember());
        Vehicle updatedVehicle;
        try{
            updatedVehicle = vehicleRepository.save(vehicle);
        }catch(Exception e){
            throw new VehicleExistException("Vehicle with plate " + vehicle.getPlate() + " already exists");
        }
        return updatedVehicle;
    }

    @Transactional
    @Override
    public void deleteVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException {
        getMemberVehicle(memberId, vehicleId);
        vehicleRepository.deleteByVehicleId(vehicleId);
    }
}
