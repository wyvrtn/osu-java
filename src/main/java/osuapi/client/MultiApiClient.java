/*
 * Jospi Copyright (C) 2024 wyvrtn <wyvrtn@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package osuapi.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import lombok.Getter;
import lombok.experimental.Delegate;
import osuapi.client.core.AbstractApiAuthorization;
import osuapi.client.core.OsuApiClient;
import osuapi.client.request.RequestBundle;
import osuapi.endpoints.ApiEndpoints;

public final class MultiApiClient<K> {
    public final ApiEndpoints endpoints;

    @Delegate(excludes=OsuApiClientDelgationExclusions.class)
    private final OsuApiClient client;

    @Getter
    private final ConcurrentMap<K, AbstractApiAuthorization> authorizationInstances;

    public MultiApiClient(AbstractApiAuthorization auth) {
        this(auth, new RequestBundle());
    }

    public MultiApiClient(AbstractApiAuthorization auth, RequestBundle bundle) {
        client = new OsuApiClient(auth, bundle);
        endpoints = client.endpoints;
        authorizationInstances = new ConcurrentHashMap<>();
    }

    public void switchInstance(K key) {
        client.updateAuthorization(authorizationInstances.get(key));
    }

    private interface OsuApiClientDelgationExclusions {
        public void updateAuthorization(AbstractApiAuthorization newAuth);
    }
}
