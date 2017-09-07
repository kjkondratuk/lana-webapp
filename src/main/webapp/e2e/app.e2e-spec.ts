import {LanaFrontendPage} from './app.po';

describe('lana-frontend App', () => {
  let page: LanaFrontendPage;

  beforeEach(() => {
    page = new LanaFrontendPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
