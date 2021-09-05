import React from 'react';

/**
 * shallow allows us to import a 'shallow' instance of our SearchFormInput component.
 * This means that any child components created within our tested components will only exist as a reference.
 */
import {shallow, configure} from 'enzyme';

import SearchFormInput from './Crawler.js';

import Adapter from "enzyme-adapter-react-16";

configure ({adapter: new Adapter()});
