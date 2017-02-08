package esp.detector;

import esp.model.Coordinate;
import esp.model.Sector;

import java.util.*;

import static esp.util.StreamUtils.createStreamBy;

public class GroupIterator implements Iterator<Group> {

    private final GroupFinder groupFinder;
    private List<Group> accountGroup;
    private Iterator<Coordinate> humanCellIterator;
    private Optional<Coordinate> nextHumanCell;


    GroupIterator(Sector sector) {
        this.groupFinder = new GroupFinder(sector);
        this.humanCellIterator = new SectorToHumanCellListConverter().convert(sector).iterator();
        accountGroup = new LinkedList<>();
        nextHumanCell = findNextUnaccountedHuman();
    }

    @Override
    public boolean hasNext() {
        return nextHumanCell.isPresent();
    }

    @Override
    public Group next() {
        if (!nextHumanCell.isPresent()) {
            throw new NoSuchElementException();
        }
        Group group = this.groupFinder.find(nextHumanCell.get());
        accountGroup.add(group);
        nextHumanCell = findNextUnaccountedHuman();
        return group;
    }

    private Optional<Coordinate> findNextUnaccountedHuman() {
        return createStreamBy(humanCellIterator)
                .filter(coordinate -> accountGroup
                        .stream()
                        .noneMatch(group -> group.inGroup(coordinate)))
                .findFirst();
    }

}
