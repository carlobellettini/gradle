/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.test.fixtures.server.http

import org.gradle.test.fixtures.resource.RemoteResource

abstract class AbstractHttpResource implements RemoteResource {

    protected HttpServer server

    public AbstractHttpResource(HttpServer server) {
        this.server = server
    }

    @Override
    URI getUri() {
        return new URI(server.uri.scheme, server.uri.authority, path, null, null)
    }

    abstract protected String getPath();

    abstract void expectGet()

    abstract void expectGetBroken()

    abstract void expectGetMissing()

    abstract void expectHead()

    abstract void expectHeadBroken()

    abstract void expectHeadMissing()

    @Override
    void expectDownload() {
        expectGet()
    }

    @Override
    void expectDownloadBroken() {
        expectGetBroken()
    }

    @Override
    void expectDownloadMissing() {
        expectGetMissing()
    }

    @Override
    void expectMetadataRetrieve() {
        expectHead()
    }

    @Override
    void expectMetadataRetrieveBroken() {
        expectHeadBroken()
    }

    @Override
    void expectMetadataRetrieveMissing() {
        expectHeadMissing()
    }
}