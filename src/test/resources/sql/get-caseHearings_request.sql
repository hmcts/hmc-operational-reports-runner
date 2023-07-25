
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000000', 'HEARING_REQUESTED','true');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000010', 'HEARING_UPDATED','true');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000009', 'HEARING_REQUESTED','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000011', 'LISTED','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000012', 'HEARING_REQUESTED','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000013', 'EXCEPTION','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000014', 'EXCEPTION','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000015', 'UPDATE_REQUESTED','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000016', 'UPDATE_REQUESTED','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000017', 'UPDATE_SUBMITTED','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000018', 'UPDATE_SUBMITTED','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000019', 'LISTED','false');
insert into hearing ( hearing_id, status, is_linked_flag) values ('2000000020', 'LISTED','false');

insert into case_hearing_request (
auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values ('t'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276233,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000000	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (101, 't'	,'hearingType2',	61,	'Priority type2',	6,'t','t','AB123',null,'BBA3'	,9372710950276233,	'2020-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC333',	't',	'2021-10-10 00:00:00',	20,	2000000010	,'f'	,	'Some listing comments2',	'Some judge2',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (102, 't'	,'hearingType3',	63,	'Priority type3',	6,'t','t','AB123',null,'BBA3'	,9372710950276233,	'2020-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC333',	't',	'2021-10-10 00:00:00',	30,	2000000009	,'f'	,	'Some listing comments3',	'Some judge3',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (103, 't'	,'hearingType3',	63,	'Priority type3',	6,'t','t','AB123',null,'BBA3'	,9372710950276233,	'2020-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC333',	't',	'2021-10-10 00:00:00',	29,	2000000009	,'f'	,	'Some listing comments3',	'Some judge3',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (104, 't'	,'hearingType4',	63,	'Priority type3',	6,'t','t','AB123',null,'BBA3'	,9856815055686759,	'2020-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC333',	't',	'2021-10-10 00:00:00',	29,	2000000011	,'f'	,	'Some listing comments3',	'Some judge3',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (105, 't'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276239,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000012	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (106, 't'	,'hearingType4',	63,	'Priority type3',	6,'t','t','AB123',null,'BBA3'	,9856815055686799,	'2020-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC333',	't',	'2021-10-10 00:00:00',	29,	2000000013	,'f'	,	'Some listing comments3',	'Some judge3',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (107, 't'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276299,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000014	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');

insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (108, 't'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276299,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000015	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (109, 't'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276299,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000016	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (110, 't'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276299,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000017	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (111, 't'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276299,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000018	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (112, 't'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276299,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000019	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');
insert into case_hearing_request (
case_hearing_id, auto_list_flag, hearing_type, required_duration_in_minutes, hearing_priority_type, number_of_physical_attendees, hearing_in_welsh_flag, private_hearing_required_flag, lead_judge_contract_type, first_date_time_of_hearing_must_be, hmcts_service_code, case_reference, hearing_request_received_date_time, external_case_reference, case_url_context_path, hmcts_internal_case_name, public_case_name, additional_security_required_flag, owning_location_id, case_restricted_flag, case_sla_start_date, hearing_request_version, hearing_id, interpreter_booking_required_flag, listing_comments, requester, hearing_window_start_date_range, hearing_window_end_date_range)
values (113, 't'	,'hearingType1',	60,	'Priority type1',	4,'f','f','AB123',null,'BBA3'	,9372710950276299,	'2021-08-10 11:20:00','EXT/REF123',	'https://www.google.com',	'Internal case name','Public case name',	't'	,'CMLC123',	't',	'2021-10-10 00:00:00',	10,	2000000020	,'t'	,	'Some listing comments1',	'Some judge1',	'2021-11-01 00:00:00',	'2021-11-12 00:00:00');


insert into cancellation_reasons (id, case_hearing_id, cancellation_reason_Type)
values (1, 105, 'reasonOne');
insert into cancellation_reasons (id, case_hearing_id, cancellation_reason_Type)
values (2, 105, 'reasonTwo');

insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (1, '2000000000', '2020-08-10 11:20:00', 'LT0001', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version, parties_notified_datetime)
values (2, '2000000010', '2021-08-10 11:20:00', 'LT0002', 'PROVISIONAL', 'AWAITING_LISTING', 1, '2020-08-10 11:20:00');
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (3, '2000000009', '2021-08-10 11:20:00', 'LT0003', 'FIXED', 'LISTED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (4, '2000000011', '2021-08-10 11:20:00', 'LT0004', 'FIXED', 'LISTED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (5, '2000000012', '2020-08-10 11:20:00', 'LT0005', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (6, '2000000013', '2021-08-10 21:20:00', 'LT0006', 'FIXED', 'LISTED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (7, '2000000014', '2020-08-10 22:40:00', 'LT0007', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (8, '2000000015', '2021-08-10 21:20:00', 'LT0008', 'FIXED', 'LISTED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (9, '2000000015', '2020-08-10 22:40:00', 'LT0009', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (10, '2000000016', '2021-08-10 21:20:00', 'LT0010', 'FIXED', 'LISTED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (11, '2000000016', '2020-08-10 22:40:00', 'LT0011', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (12, '2000000017', '2020-08-10 22:40:00', 'LT0012', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (13, '2000000017', '2021-08-10 21:20:00', 'LT0013', 'FIXED', 'LISTED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (14, '2000000018', '2020-08-10 22:40:00', 'LT0014', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (15, '2000000018', '2021-08-10 21:20:00', 'LT0015', 'FIXED', 'LISTED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (16, '2000000019', '2020-08-10 22:40:00', 'LT0016', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (17, '2000000019', '2020-08-10 22:40:00', 'LT0017', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (18, '2000000019', '2020-08-10 22:40:00', 'LT0018', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (19, '2000000019', '2020-08-10 22:40:00', 'LT0019', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (20, '2000000020', '2021-08-10 22:40:00', 'LT0020', 'DRAFT', 'CASE_CREATED', 1);
insert into hearing_response(hearing_response_id, hearing_id, received_date_time, listing_transaction_id, listing_status, listing_case_status, request_version)
values (21, '2000000020', '2021-08-17 22:40:00', 'LT0021', 'DRAFT', 'CASE_CREATED', 1);



insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (1, 1, '2021-01-10 11:20:00', '2021-08-10 11:20:00', 'venue1-1', 'room1-1');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (2, 1, '2020-01-10 11:20:00', '2020-08-10 11:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (3, 2, '2020-01-10 11:20:00', '2020-08-10 11:20:00', 'venue2-1', 'room2-1');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (4, 3, '2020-01-10 11:20:00', '2020-08-10 11:20:00', 'venue3-1', 'room3-1');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (5, 4, '2020-01-10 11:20:00', '2020-08-10 11:20:00', 'venue3-1', 'room3-1');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (6, 5, '2021-01-10 11:20:00', '2021-08-10 11:20:00', 'venue1-1', 'room1-1');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (7, 5, '2020-01-10 11:20:00', '2020-08-10 11:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (8, 6, '2021-01-10 11:20:00', '2021-01-14 11:20:00', 'venue1-1', 'room1-1');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (9, 6, '2021-02-10 11:20:00', '2021-02-15 11:20:00', 'venue1-1', 'room1-1');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (10, 7, '2020-02-11 11:20:00', '2020-02-21 11:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (11, 7, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (12, 8, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (13, 8, '2020-04-01 11:20:00', '2020-04-12 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (14, 9, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (15, 9, '2022-09-01 11:20:00', '2022-10-01 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (16, 10, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (17, 10, '2022-09-01 11:20:00', '2022-09-09 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (18, 11, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (19, 11, '2022-09-01 11:20:00', '2022-10-01 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (20, 12, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (21, 12, '2022-09-01 11:20:00', '2022-09-09 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (22, 13, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (23, 13, '2022-09-01 11:20:00', '2022-09-08 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (24, 14, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (25, 14, '2022-09-01 11:20:00', '2022-09-09 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (26, 15, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (27, 15, '2022-09-01 11:20:00', '2022-10-01 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (28, 16, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (29, 16, '2022-09-01 11:20:00', '2022-09-09 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (30, 17, '2020-03-01 11:20:00', '2020-03-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (31, 17, '2022-09-01 11:20:00', '2022-09-09 13:20:00', 'venue1-2', 'room1-2');

insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (32, 18, '2022-09-11 11:20:00', '2022-09-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (33, 18, '2022-09-12 11:20:00', '2022-09-12 16:30:00', 'venue1-2', 'room1-2');


insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (34, 20, '2022-10-01 11:20:00', '2022-10-12 13:20:00', 'venue1-2', 'room1-2');
insert into hearing_day_details(hearing_day_id, hearing_response_id, start_date_time, end_date_time, venue_id, room_id)
values (35, 20, '2022-10-02 11:20:00', '2022-10-14 13:20:00', 'venue1-2', 'room1-2');



insert into hearing_day_panel(id, hearing_day_id, panel_user_id,is_presiding) values (1, 1, 'panel1-1','true');
insert into hearing_day_panel(id, hearing_day_id, panel_user_id,is_presiding) values (2, 1, 'panel1-2','false');
insert into hearing_day_panel(id, hearing_day_id, panel_user_id,is_presiding) values (3, 2, 'panel1-2',null);
insert into hearing_day_panel(id, hearing_day_id, panel_user_id,is_presiding) values (4, 3, 'panel2-1','true');
insert into hearing_day_panel(id, hearing_day_id, panel_user_id,is_presiding) values (5, 4, 'panel3-1','false');
insert into hearing_day_panel(id, hearing_day_id, panel_user_id,is_presiding) values (6, 5, 'panel3-1','false');
insert into hearing_day_panel(id, hearing_day_id, panel_user_id,is_presiding) values (7, 6, 'panel3-1','false');
insert into hearing_day_panel(id, hearing_day_id, panel_user_id,is_presiding) values (8, 6, 'panel3-1','false');

insert into hearing_attendee_details(id, hearing_day_id, party_id, party_sub_channel_type) values (1, 1, 'party1-1','subChannel1-1');
insert into hearing_attendee_details(id, hearing_day_id, party_id, party_sub_channel_type) values (2, 1, 'party1-2','subChannel1-2');
insert into hearing_attendee_details(id, hearing_day_id, party_id, party_sub_channel_type) values (3, 3, 'party2-1','subChannel2-1');
insert into hearing_attendee_details(id, hearing_day_id, party_id, party_sub_channel_type) values (4, 4, 'party3-1','subChannel3-1');

insert into hearing_channels(hearing_channels_id,hearing_channel_type,case_hearing_id) values (1, 'Paper', 102);
insert into hearing_channels(hearing_channels_id,hearing_channel_type,case_hearing_id) values (2, 'Email', 102);

insert into hearing_attendee_details(id, hearing_day_id, party_id, party_sub_channel_type) values (5, 5, 'party3-1','subChannel3-1');

