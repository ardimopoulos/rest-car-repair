package rest.car_repair.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rest.car_repair.domain.Member;
import rest.car_repair.domain.Vehicle;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleExistException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.car_repair.repositories.VehicleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleServiceImp implements VehicleService {

    private VehicleRepository vehicleRepository;
    private MemberService memberService;

    @Override
    public List<Vehicle> getAllVehiclesByMember(Long memberId) throws VehicleNotFoundException, MemberNotFoundException {
        Member member = memberService.getMemberById(memberId);
        List<Vehicle> memberVehicles = member.getVehicles();

        if(memberVehicles.isEmpty()){
            throw new VehicleNotFoundException("There are not found vehicle for user with id " + memberId);
        }

        return memberVehicles;
    }

    @Override
    public Vehicle getVehicleByMember(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        memberService.getMemberById(memberId);
        Optional<Vehicle> persistenceVehicle = vehicleRepository.findById(vehicleId);

        if(!persistenceVehicle.isPresent()){
            throw new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found");
        }

        Vehicle vehicle = persistenceVehicle.get();

        if(vehicle.getMember().getUserId() != memberId){
            throw new VehicleNotReferredToUserException("Vehicle with id " + vehicleId + " is not referred to user with id " + memberId);
        }

        return vehicle;
    }

    @Override
    public Vehicle saveVehicle(Long memberId, Vehicle vehicle) throws VehicleExistException, MemberNotFoundException {
        Member member = memberService.getMemberById(memberId);
        try {
            vehicle.setMember(member);
            return vehicleRepository.save(vehicle);
        } catch (Exception e){
            throw new VehicleExistException("Vehicle with plate " + vehicle.getPlate() + " already exists");
        }
    }

    @Override
    public Vehicle updateVehicle(Long memberId, Long vehicleId, Vehicle vehicle) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException, MemberNotFoundException {
        getVehicleByMember(memberId, vehicleId);
        vehicle.setVehicleId(vehicleId);
        return saveVehicle(memberId,vehicle);
    }

    @Transactional
    @Override
    public void deleteVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        getVehicleByMember(memberId, vehicleId);
        vehicleRepository.deleteById(vehicleId);
    }
}
