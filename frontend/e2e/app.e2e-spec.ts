import { LoansUiPage } from './app.po';

describe('loans-ui App', function() {
  let page: LoansUiPage;

  beforeEach(() => {
    page = new LoansUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
