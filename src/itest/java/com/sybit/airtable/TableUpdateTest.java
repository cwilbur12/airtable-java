/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.mock.WireMockBaseTest;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author fzr
 */
@Ignore
public class TableUpdateTest extends WireMockBaseTest {

    @Test
    public void testUpdateActor() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{


        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor billMurray = new Actor();
        billMurray.setId("recAmED5bFI4Kk7BG");
        billMurray.setName("Neuer Name");

        Actor updated = actorTable.update(billMurray);

        assertEquals(updated.getName(),"Neuer Name");
        assertNotNull(updated.getBiography());
        assertNotNull(updated.getFilmography());
        assertNotNull(updated.getPhoto());
        assertNotNull(updated.getId());

    }
}
