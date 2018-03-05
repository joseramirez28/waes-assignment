package org.jr.waes.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jr.waes.base64.Base64DataComparator;
import org.jr.waes.dao.BinaryDataDao;
import org.jr.waes.dto.BinaryDataComparison;
import org.jr.waes.dto.DiffResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;

/**
 * Controller for Assigment endpoints
 * 
 * @author Jose Ramirez
 *
 */
@RestController
@Api(value = "WaesController")
@RequestMapping(path = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WaesController {

	private static final Logger LOG = LoggerFactory.getLogger(WaesController.class);
	private static final String CACHE_CONTROL_VALUE = "cache, must-revalidate";

	@Autowired
	private BinaryDataDao dao;

	@RequestMapping(value = {
			"/diff/{id}/left" }, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> diffLeft(@PathVariable("id") Integer id, @RequestBody DiffRequest request) {

		Preconditions.checkArgument(request != null, "request parameter cannot be null");
		LOG.debug("Adding Left data for id: " + id);
		dao.saveOrUpdateLeft(id, request);
		return new ResponseEntity<>("{\"status\": \"Left data added correctly\"}", generateResponseHeaders(request),
				HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/diff/{id}/right" }, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> diffRight(@PathVariable("id") Integer id, @RequestBody DiffRequest request) {

		Preconditions.checkArgument(request != null, "request parameter cannot be null");
		LOG.debug("Adding Right data for id: " + id);
		dao.saveOrUpdateRight(id, request);
		return new ResponseEntity<>("{\"status\": \"Right data added correctly\"}", generateResponseHeaders(request),
				HttpStatus.OK);
	}

	@RequestMapping(value = { "/diff/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<DiffResult> getDiff(@PathVariable("id") Integer id) {

		LOG.debug("Comparing base64 data for id : " + id);
		Optional<BinaryDataComparison> result = dao.getDiff(id);

		// ID data not found
		if (!result.isPresent()) {
			throw new IllegalArgumentException("Requested Id not found: " + id);
		}

		// Get left and right from DB
		String left = result.get().getLeft();
		String right = result.get().getRight();

		// Missing data for either left or right
		boolean emptyLeft = StringUtils.isEmpty(left);
		boolean emptyRight = StringUtils.isEmpty(right);
		if (emptyLeft || emptyRight) {
			throw new IllegalArgumentException("Requested Id is missing data: " + id + ", Left Missing: " + emptyLeft
					+ " , Right Missing: " + emptyRight);
		}

		DiffResult diff = new Base64DataComparator().compareBinaries(left, right);
		return new ResponseEntity<>(diff, HttpStatus.OK);
	}

	/**
	 * Configure base request headers
	 *
	 * @param request
	 *            The incoming request object.
	 * @return HttpHeaders object
	 */
	private HttpHeaders generateResponseHeaders(DiffRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE));
		headers.setCacheControl(CACHE_CONTROL_VALUE);
		return headers;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

}
