@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDateTime inspectionDate;

@Column(nullable = false)
private ReservationStatus status = ReservationStatus.PENDING; 