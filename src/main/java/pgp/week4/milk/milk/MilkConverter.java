package pgp.week4.milk.milk;

public class MilkConverter {
    static Milk fromDTO(CreateMilkDTO dto) {
        return new Milk(dto.id(), dto.name(), dto.type(), dto.storage());
    }

    static MilkResponseDTO toResponseDTO(Milk entity) {
        return new MilkResponseDTO(
                entity.getId()
                , entity.getName()
                , entity.getType()
                , entity.getStorage());
    }
}
