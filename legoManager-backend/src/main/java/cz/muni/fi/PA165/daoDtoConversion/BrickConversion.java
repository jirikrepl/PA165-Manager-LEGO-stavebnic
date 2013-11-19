package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.muni.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.entity.Brick;

/**
 * methods of this class converts brick entity objects to Dto and back
 * @author Jiri Krepl
 */
public class BrickConversion {

    /**
     * creates DTO object from this entity
     * @return BrickDto dto object
     */
    public static BrickDto convertToDto(Brick brick) {
        if (brick == null) {
            throw new IllegalArgumentException("Entity can not be NULL");
        }
        BrickDto brickDto = new BrickDto();
        brickDto.setId(brick.getId());
        brickDto.setName(brick.getName());
        brickDto.setColor(brick.getColor());
        brickDto.setDescription(brick.getDescription());
        return brickDto;
    }
    
    /**
     * creates entity Brick from this dto object
     * @return Brick entity
     */
    public static Brick convertToEntity(BrickDto brickDto) {
        if (brickDto == null) {
            throw new IllegalArgumentException("Dto can not be NULL");
        }
        Brick brick = new Brick();
        brick.setColor(brickDto.getColor());
        brick.setDescription(brickDto.getDescription());
        brick.setName(brickDto.getName());
        return brick;
    }
}
