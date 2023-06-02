package pgp.week4.milk.milk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilkService {
    @Autowired
    MilkRepository milkRepository;


    public MilkService() {
    }

    public MilkService(MilkRepository repo) {
        this.milkRepository = repo;
    }

    public List<Milk> getAllMilks() {
        return milkRepository.listMilks();
    }

    public Milk findById(String id) {
        return milkRepository.getById(id);
    }

    public Milk saveMilk(Milk milk) {
        return milkRepository.saveMilk(milk);
    }

    public void saveMilks(List<Milk> milks) {
        milkRepository.saveMilks(milks);
    }

    public Milk updateMilkData(@org.jetbrains.annotations.NotNull Milk newMilkData) {
        Milk storedMilk = findById(newMilkData.getId());
        if (storedMilk == null) {
            return null;
        }
        if (newMilkData.getStorage() < 0 | (storedMilk.getStorage() - newMilkData.getStorage() < 0)) {
            throw new IllegalArgumentException();
        }

        if (newMilkData.getStorage() >= 0 & (storedMilk.getStorage() - newMilkData.getStorage() >=0)) {
            storedMilk.setStorage(storedMilk.getStorage()- newMilkData.getStorage());
        }
        return milkRepository.saveMilk(storedMilk);
    }

    public void deleteMilk(String id) {
        milkRepository.deleteMilk(id);
    }


}
