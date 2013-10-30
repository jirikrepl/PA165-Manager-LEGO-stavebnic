package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.entity.Brick;

public class BrickConversion {

    /**
     * creates DTO object from this entity
     * @return BrickDto dto object
     */
    public static BrickDto convertToDto(Brick brick) {
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
        Brick brick = new Brick();
        brick.setColor(brickDto.getColor());
        brick.setDescription(brickDto.getDescription());
        brick.setName(brickDto.getName());
        return brick;
    }
}
