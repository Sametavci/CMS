package backend.infrastructure.persistence.adapter;

import backend.domain.models.DomainMovie;
import backend.domain.ports.repositorys.ISessionRepository;
import backend.infrastructure.persistence.entities.Movie;
import backend.infrastructure.persistence.entities.Reservation;
import backend.infrastructure.persistence.repositorys.IReservationJpaRepository;
import backend.domain.models.DomainReservation;
import backend.domain.ports.repositorys.IReservationRepository;
import backend.infrastructure.persistence.mapper.ReservationMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationRepositoryAdapter implements IReservationRepository {

    private final IReservationJpaRepository reservationJpaRepository;
    private final ISessionRepository sessionRepository;
    private final ReservationMapper mapper;

    @Autowired
    public ReservationRepositoryAdapter(IReservationJpaRepository reservationJpaRepository, ReservationMapper mapper, ISessionRepository sessionRepository) {
        this.reservationJpaRepository = reservationJpaRepository;
        this.mapper = mapper;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public DomainReservation save(DomainReservation entity) {
        Reservation reservationEntity = mapper.toEntity(entity);
        Reservation savedEntity = reservationJpaRepository.save(reservationEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainReservation> findById(Long id) {
        return reservationJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainReservation> findAll() {
        return reservationJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        reservationJpaRepository.deleteById(id);
    }

    @Override
    public Double calculateDiscountedPrice(Long id, Double discountPercentage) {
        Reservation reservation = reservationJpaRepository.findById(id).orElseThrow();

        Double originalPrice = reservation.getSession().getPrice();

        if (originalPrice <= 0 || discountPercentage <= 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Invalid price or discount percentage");
        }

        return originalPrice - (originalPrice * (discountPercentage / 100));
    }

    @Override
    public boolean checkCustomerDiscountEligibility(Long customerId) {

        return false;
    }

    public DomainReservation update(DomainReservation dto, Long id){
        DomainReservation databaseElement = findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find" + id)
        );
        Reservation entity = mapper.toEntity(databaseElement);
        Reservation savedEntity = reservationJpaRepository.save(mapper.update(entity, dto));
        return mapper.toDomain(savedEntity);

    }
}
